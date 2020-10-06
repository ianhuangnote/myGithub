package openssl;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteException;
import org.apache.commons.exec.Executor;
import org.apache.commons.exec.PumpStreamHandler;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.bouncycastle.openssl.PEMReader;
import org.bouncycastle.openssl.PEMWriter;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

public class GenCsr {
    public static final int DEFAULT_CMD_TIMEOUT = 3000;
    public static final int CMD_EXIT_SUCCEED = 0;
    public static final String CSR_EXT = ".csr";
    public static final String KEY_EXT = ".key";
    public static final String CSR_TMP_DIR = "/tmp/csr/";

    public static void main(String[] args) throws Exception {

        KeyPairGenerator keyGen = null;
        try {
            keyGen = KeyPairGenerator.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
        }
        keyGen.initialize(2048);
        KeyPair keypair = keyGen.genKeyPair();
        String privateKey = GenCsr.convertToPemString((keypair.getPrivate()));
        String csr = null;
        String csrProperties = "/C=GB/ST=London/L=London/O=GlobalSecurity/OU=IT Department/CN=example.com";
        System.out.println("original properties: " + csrProperties);
//        String escapeProperties = StringUtils.wrapIfMissing(StringEscapeUtils.escapeJava(csrProperties.replaceAll(" ","\\ ")), '"');
//        String escapeProperties = StringUtils.wrapIfMissing(StringEscapeUtils.escapeJava(csrProperties), '"');
        String escapeProperties = csrProperties;
//        String escapeProperties = csrProperties.replace(" ",File.separator);
//        String escapeProperties = csrProperties.replaceAll(" ", "\\\\ ");
        System.out.println("escaped properties: " + escapeProperties);
        try {
            csr = GenCsr.genAndGetCsrViaOpenssl(privateKey, "testCsr", escapeProperties);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("csr = " + csr);
    }

    public static void appendLine(StringBuilder sb, String str) {
        sb.append(str != null ? str : "").append(System.getProperty("line.separator"));
    }

    public static String genAndGetCsrViaOpenssl(String privateKey, String csrName, String csrProperties) throws Exception {
        String tmpPrivateKey = CSR_TMP_DIR + csrName + KEY_EXT;
        File privateKeyFile = new File(tmpPrivateKey);
        MyFileUtils.writeStringToFile(privateKeyFile, privateKey);
        String tmpOutputCsr = CSR_TMP_DIR + csrName + CSR_EXT;
        genCsrViaOpenssl(tmpPrivateKey, tmpOutputCsr, csrProperties);
        String csrStr = loadCsr(tmpOutputCsr);
        File tmpCsr = new File(tmpOutputCsr);
        if(tmpCsr.exists()) {
//            boolean isSuccessful = tmpCsr.delete();
        }
        return csrStr;
    }

    public static void genCsrViaOpenssl(String tmpKeyPath, String tmpOutputCsr, String csrProperties) throws Exception {
        StringBuilder sb = new StringBuilder("Generating CSR via Openssl...");
        appendLine(sb, "\n============ Generating CSR ==============");
        appendLine(sb, "- To be used temp key path: " + tmpKeyPath);
        appendLine(sb, "- To be output CSR: " + tmpOutputCsr);
        appendLine(sb, "- To be included properties: " + csrProperties);
//        System.out.println(sb.toString());
        Executor executor = new DefaultExecutor();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PumpStreamHandler streamHandler = new PumpStreamHandler(outputStream);
        executor.setStreamHandler(streamHandler);
        int exitValue = 0;
        try {
            CommandLine command = new CommandLine("/usr/bin/openssl")
                    .addArgument("req")
                    .addArgument("-new ")
                    .addArgument("-key ")
                    .addArgument(tmpKeyPath)
                    .addArgument("-out ")
                    .addArgument(tmpOutputCsr)
                    .addArgument("-subj ")
                    .addArgument(csrProperties, false);
//                    .addArgument("\'" + csrProperties + "\'");
            System.out.println(command.toString());
            exitValue = executor.execute(command);

        } catch (ExecuteException e) {
            exitValue = 1;
            System.out.println(outputStream.toString());
            e.printStackTrace();

//            Process p = Runtime.getRuntime().exec(new String[] {
//                    "/usr/bin/openssl",
//                    "req",
//                    "-new",
//                    "-key",
//                    "/tmp/csr/testCsr.key",
//                    "-out",
//                    "/tmp/csr/testCsr_coo.csr",
//                    "-subj",
//                    "/C=GB/ST=London/L=London/O=GlobalSecurity/OU=IT\\ Department/CN=example.com",
//            });
//            System.out.println("hahahaha = " + p.waitFor());
//            System.out.println("" + IOUtils.toString(p.getErrorStream()));
        }
        if(exitValue != CMD_EXIT_SUCCEED) {
            throw new Exception();
        }

        File tmpKey = new File(tmpKeyPath);
        if(tmpKey.exists()) {
//            boolean isSuccessful = tmpKey.delete();
        }
    }

    public static String loadCsr(String csrPath){
        FileInputStream fis = null;
        String result ="";
        try {
            StringWriter sw = new StringWriter();
            fis = new FileInputStream(new File(csrPath));
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));
            PEMReader pemReader = new PEMReader(br);

            result = convertToPemString(pemReader.readObject());
        }  catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                }
            } catch(IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public static String convertToPemString(Object obj) {
        PEMWriter pm = null;
        StringWriter sw = null;
        try {
            sw = new StringWriter();
            pm = new PEMWriter(sw);
            pm.writeObject(obj);
            pm.flush();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (pm != null) {
                    pm.close();
                }
            } catch(IOException e) {
                e.printStackTrace();
            }
        }
        return sw.toString();
    }

}
