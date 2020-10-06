package cert;

import org.apache.commons.io.IOUtils;
import org.bouncycastle.openssl.PEMReader;
import org.bouncycastle.openssl.PasswordFinder;
import org.bouncycastle.util.encoders.Base64;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.io.FileUtils;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.security.KeyPair;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

public class LoadPriKey {

    private static PasswordFinder getPasswordFinder(final String password) {
        return new PasswordFinder() {
            @Override
            public char[] getPassword() {
                if (password != null) {
                    final char[] pass = password.toCharArray();
                    return pass;
                } else {
                    return null;
                }
            }
        };
    }

    public static X509Certificate load(String certData)
            throws CertificateException, IOException {
        return load(new ByteArrayInputStream(certData.getBytes()));
    }

    public static X509Certificate load(InputStream is)
            throws CertificateException, IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
            String line = null;
            while ((line = br.readLine()) != null) {
                if (line.indexOf("-----BEGIN CERTIFICATE") != -1) {
                    ByteArrayInputStream bais = new ByteArrayInputStream(readBytes(
                            br, "-----END CERTIFICATE"));
                    return (X509Certificate) CertificateFactory
                            .getInstance("X.509").generateCertificate(bais);
                }
            }
        } catch (CertificateException ex) {
            // In SCG-76850, we encountered some certificate can't be parsed by JDK CertificateFactory
            // But it can be parsed by bouncyCastle's library
            // So we need try again when parse certificate by JDK library fail.
            // Actually I didn't get why such situation happen
            return loadCertificate(IOUtils.toString(is));
        }
        return null;
    }

    public static List<X509Certificate> loadCertificates(InputStream is)
            throws CertificateException, IOException {
        List<X509Certificate> certs = new ArrayList<X509Certificate>();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        PEMReader pemReader = new PEMReader(br);
        try {
            Object obj = null;
            while ((obj = pemReader.readObject()) != null) {
                if (obj instanceof X509Certificate) {
                    certs.add((X509Certificate) obj);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            pemReader.close();
        }
        return certs;
    }

    public static X509Certificate loadCertificate(String certData)
            throws CertificateException, IOException {
        List<X509Certificate> certBundle = loadCertificates(new ByteArrayInputStream(
                certData.getBytes()));
        return certBundle.size() > 0 ? certBundle.get(0) : null;
    }

    private static byte[] readBytes(BufferedReader reader, String endMarker)
            throws IOException {
        String line = null;
        StringBuffer buf = new StringBuffer();

        while ((line = reader.readLine()) != null) {
            if (line.indexOf(endMarker) != -1) {
                break;
            }
            buf.append(line.trim());
        }

        if (line == null) {
            throw new IOException(endMarker + " not found");
        }

        return Base64.decode(buf.toString());
    }

    public static void writeStringToFile(File file, String data) throws IOException {
        writeStringToFile(file, data, null, false);
    }

    public static void writeStringToFile(File file, String data, String encoding, boolean append) throws IOException {
        OutputStream out = null;
        try {
            out = openOutputStream(file, append);
            IOUtils.write(data, out, encoding);
            out.close(); // don't swallow close Exception if copy completes normally
        } finally {
            IOUtils.closeQuietly(out);
        }
    }

    public static FileOutputStream openOutputStream(File file, boolean append) throws IOException {
        if (file.exists()) {
            if (file.isDirectory()) {
                throw new IOException("File '" + file + "' exists but is a directory");
            }
            if (file.canWrite() == false) {
                throw new IOException("File '" + file + "' cannot be written to");
            }
        } else {
            File parent = file.getParentFile();
            if (parent != null) {
                if (!parent.mkdirs() && !parent.isDirectory()) {
                    throw new IOException("Directory '" + parent + "' could not be created");
                }
            }
        }
        return new FileOutputStream(file, append);
    }

//    public static boolean isKeyValid(String certpath, String keypath) throws Exception {
//        String result = null;
//        String command = CERTS_UTIL_SCRIPT + " verifykey " + certpath + " " + keypath;
//
//        try {
//            result = OSShell.executeOSShell(command, -1).trim();
//
//            if(!StringUtils.hasText(result)) {
//                return false;
//            }
//
//        } catch(Exception ex) {
//            return false;
//        }
//
//        if(!"1".equals(result)) {
//            return false;
//        }
//
//        return true;
//    }

    public static void main(String[] args) {
        String privateKeyPemData = "-----BEGIN RSA PRIVATE KEY-----\n" +
                "MIIEpQIBAAKCAQEApEHoXUWcGPJOadbYGisNFN1xlew145/ohKEUXPSdtIL/D8AU\n" +
                "ydBNKI/GRtc/gkXWxFdU4LeMpFpHBzzlQ5+wNsCMnXfNWfcUjEJ6QlI/HDVdqJMg\n" +
                "BJP7WW7ZsL1o431NYG8FLtoCR97LpeSIjJbJNeSCWsV8bCPHGdXa1/tPpWYW5N1M\n" +
                "acWBOwhxNYG/1jE2Ks1uyyb92ybs/kqicKWeLMORG5K/tjYjyng8Lp+rj5nSiY0J\n" +
                "yZuWEHRWolqSoM9kRstyU1X/fSfazx1s6rzY+bfTYgPj0SxWvShg0ApGA4SqmWyH\n" +
                "4jM1pSYu7g5sH1Xr1L+M6OKxRdA/GublFak+zQIDAQABAoIBAQCWLpCXHzsu7/TT\n" +
                "/pTNsEkgp6vf6P4Ipj+BbInix+GdlxxAOV6Ie0Tg7jvar5mosdRA5px7bjdQWh6R\n" +
                "NpNkdhA2r2yh2VTPigKmj22bT/nV/9EnfYWLygXJannth/Dr9rgVkMyLXtcxATrv\n" +
                "7fkPmhpUhR7OgbxWX4v+SjLywRu8XBlRjFYtaenvPFqdpjr83CmBYtJWTVRLld9Y\n" +
                "fpVYxguRXJPnU0ruJ+Q7eWNbPJi2GU6bFoOBsPUnBpByIe2sjdYv0Q6DVdgax+2n\n" +
                "xHfN7MvyvOGBBMuQL4cZEIsLS93GumLcjtwxTBovE4bLOqXZpTc51eDSQQ+eweeh\n" +
                "2KEepQDhAoGBANA/IB5UEDkT/4KUBRcGjs4s9IAfSO8oM09hs5sHQH6rJhJuShBg\n" +
                "libjWDB4hrqVafEbpsH/Zaf7dcjxXQ4+t7v7ndfj2JNw+k5GwsU2qpcQM42pUhvK\n" +
                "XKRdcBedqQ9uvbfg3eYVNhTpb83M2k5sW5+PBHgH0c9WOg0+HPn46FGVAoGBAMns\n" +
                "c385qLleGWcIU3EgKYUpE8r4uATGXTxN7K9Prax11fx4fYlM9Q1FzzBnQtKkG9uM\n" +
                "hu7jCyOvwDOetAsG01YE9BRoY0p0xaQDsu0+/9lExgjia7AJq0l+wnUxjAnRXKAX\n" +
                "PJInqaL9iod3TRqz9/c/0UcPmaowAZ/JCoUWvNpZAoGBAJqtKIgeXGZo6jHtC9LM\n" +
                "dinBzmGT2zJ4I5Rkt2kA+/CZ4w9xh+WeOCjuWES4YSoZbLU/mp8hujoJVrVIQZwh\n" +
                "DYu9GsKOZAEvu1uEx4qATjkiOYiRKYGmzyTumiGQY1ApKxV3GpqZSrBBq1+rNsNl\n" +
                "TLC5I9KbzEU6gKfxaMhrDvP5AoGAc8I1OFOS0aoLgBC63Vil1OwGp87MmBgsLT0z\n" +
                "GhHgq8TmBxYASe0vcwt5SvN30JA6VDQMyvioUy4XTsCF0Oh50Y+HageqHG4zO+LO\n" +
                "+cmrHSCbM06yk7oHU1vzGQJtmNdIdZK8q8FHM+SMPTr8nflch4czb7yWyIU6+SJl\n" +
                "AIVXgQkCgYEAjLB6Td474ARbpt25+zqDYNZF8KNPJvzHrLVfEvelH7P0vpFcqtDE\n" +
                "aFM+4Nd/7ol9kM3NkVZAPBFR3Sw0x0oRoZeLKkoSr85WHAjsvfpyk+822HlznY0U\n" +
                "ojvmBUIr+xuVM3PRGHMCm9t+fk7+4laRuEYBG5kRu6KXtqxC+0ppl8I=\n" +
                "-----END RSA PRIVATE KEY-----";
//        String davidPriKey = "-----BEGIN ENCRYPTED PRIVATE KEY-----\n" +
//                "MIIFHDBOBgkqhkiG9w0BBQ0wQTApBgkqhkiG9w0BBQwwHAQIOnFWCd6yxXYCAggA\n" +
//                "MAwGCCqGSIb3DQIJBQAwFAYIKoZIhvcNAwcECJ8rS1sLVoDgBIIEyA29w96erRuy\n" +
//                "Kivbd5tzFVmapP+4VfHLC85Ri9vhZxEkR7h08nfx/VQ5Mj4gnDEIz9trbQG6PFmC\n" +
//                "8SAc4AudufVv18xcuIsu1zS0p2UG5kbQb2oeMmDv+9zcbJUnNQ9s5vsGHlF+cdDZ\n" +
//                "lnW6UaYkj72HyZrZH22KbqAxfENmtz1UnwIeCUBaUyqrDgXYsITAaVBnuUO32vxp\n" +
//                "5l+zbH9IjoUcb3J2oCKnrSlVWpQHMfGsy6N5mBpavX99i4naHSuVqT54IAGlKy6c\n" +
//                "HB+d/rRyN8MDT/ZJ4K6xecWJgctdBI7VY3bwSeMkBarOJ5CMniGAkpK6CH92h9Tm\n" +
//                "gcUl50q62qYVQFlzRRrhA5KTHmz0jAC8U1vqumFlrJLuYPmrAezGSSpGkqPEyZ/5\n" +
//                "68ZfZhqtTxyptNwOzYNABDagqD0BG7F4ok6rOulkrh1/ikCb5EJIkjNDXC3bhkHa\n" +
//                "buTLziS26jffWVONXq2GcQDDQyPtMf6okw8ogUy4njWT8TX64NbLJY/RDi8nbnZ9\n" +
//                "pAsXNJY22wIgPCFQ2e1/DeONr/QEb3k+FKgtrs1ZDEN0XO8ElkxQXJ7Q57JOC0fz\n" +
//                "ZsvIY68CLX5bd5UP/AfKIwdvd9vNEsmYT95jdFEzn7/Xc3PpF2xlgkq57hoJ6EXm\n" +
//                "Kyp+MtFtZnGwCzKiDRs671ButU7dHSRs3biO9IrL/5aiw1oKTYm3agW+0AzHbjvc\n" +
//                "VMLGYHVKTxTvypDPk1Z01AN/5aCSCldTD8vKgcHawzNIhhGKxkJgry/lF2XrmXJD\n" +
//                "mX3LjWNngwJP6dXVzAYsc9YumDG8y1/9qo+G6y7M+0IlOV4NNkv08tuq8TwEXNbk\n" +
//                "Pa2QN9rip6FQ16uJgNCCgZkyeeBeL4LhEacHjzOeAXTBJ4k8p2H4/+T0TL0VG9CU\n" +
//                "ZGmzadYYNzIaQ23Xlcr8P8/dSkH+8Arg6PamK7TkJuU9+E6TzgXFDrTpvxkdGCL8\n" +
//                "mjJDDP+RNWuoNO8pk9ecGXVtA6JqL1xigXw0gArivGA7hgOLRfUiVJoYjQpkD5wl\n" +
//                "+/+mf7gAmdg89DHCrxzOckhDTbgpnLVk2zmwibKZNHMHK4KnjPwOmuP/S5HmF7p1\n" +
//                "+evdyppNIGnnnCn1HjufNMZdaORNQulRGZFwb0Gt/pocBZH/6asQXLiPQky32WJi\n" +
//                "D4mIY9ZC8Pl7y5+jrJ76FHgniAE+EIBnYisw8cS1k0HBE69Ew35p2s0z1MX4AMn/\n" +
//                "D5Jo8mHGPSuG139oHhVhZoIpSBbBFdlpqEHuBcgwqTnMFMp+jtY76FEfBzISULVR\n" +
//                "OlYNdkzUpYPwAuI0JTNbeO8zOyLBXD5NO1Agcj62UMbSnR/JfObblVwJ/kH25tvl\n" +
//                "Fme83U3ujB2T0kknwCNxDvsXP0D+pthQADVRpVaScnrX1fP21RzzMvdIEEgNO0TQ\n" +
//                "43OZkUFV6kQjzY9iUz3IMehu0vy6im3KtRI5/UyDie1YDtNrbDx0JePIwOWVIdO+\n" +
//                "r6WK7glMvfdtkE6jBsv+WxHqgv0JBvCj6r0MifhRYXE1GYjQJsuXyyj4AuwZ6Y+x\n" +
//                "pWJcCaQ8SSV9SvNAwmc6ndwpM6uWHdqDCs3rBcUtpy7Ge9tx7+xKO2F4phS5nXpI\n" +
//                "AbCSgePZQODLRKbBYvSIQw==\n" +
//                "-----END ENCRYPTED PRIVATE KEY-----";
        String privateKeyPassword = null;
        String certPemData = "-----BEGIN CERTIFICATE-----\n" +
                "MIIEwTCCA6mgAwIBAgICEAMwDQYJKoZIhvcNAQELBQAwWzELMAkGA1UEBhMCVVMx\n" +
                "CzAJBgNVBAgMAkNBMQ4wDAYDVQQKDAVBcnJpczEWMBQGA1UECwwNUnVja3VzTmV0\n" +
                "d29yazEXMBUGA1UEAwwOSW50ZXJtZWRpYXRlQ0EwHhcNMjAwOTI5MDIyMTI2WhcN\n" +
                "MjEwOTI5MDIyMTI2WjBnMQswCQYDVQQGEwJUVzELMAkGA1UECAwCVFcxDzANBgNV\n" +
                "BAcMBlRhaXBlaTERMA8GA1UECgwIU29mdHdhcmUxDzANBgNVBAsMBlJ1Y2t1czEW\n" +
                "MBQGA1UEAwwNc3oucnVja3VzLmNvbTCCASIwDQYJKoZIhvcNAQEBBQADggEPADCC\n" +
                "AQoCggEBAKRB6F1FnBjyTmnW2BorDRTdcZXsNeOf6IShFFz0nbSC/w/AFMnQTSiP\n" +
                "xkbXP4JF1sRXVOC3jKRaRwc85UOfsDbAjJ13zVn3FIxCekJSPxw1XaiTIAST+1lu\n" +
                "2bC9aON9TWBvBS7aAkfey6XkiIyWyTXkglrFfGwjxxnV2tf7T6VmFuTdTGnFgTsI\n" +
                "cTWBv9YxNirNbssm/dsm7P5KonClnizDkRuSv7Y2I8p4PC6fq4+Z0omNCcmblhB0\n" +
                "VqJakqDPZEbLclNV/30n2s8dbOq82Pm302ID49EsVr0oYNAKRgOEqplsh+IzNaUm\n" +
                "Lu4ObB9V69S/jOjisUXQPxrm5RWpPs0CAwEAAaOCAYEwggF9MAkGA1UdEwQCMAAw\n" +
                "EQYJYIZIAYb4QgEBBAQDAgZAMDMGCWCGSAGG+EIBDQQmFiRPcGVuU1NMIEdlbmVy\n" +
                "YXRlZCBTZXJ2ZXIgQ2VydGlmaWNhdGUwHQYDVR0OBBYEFP2pq/ZOjwNbozErlxTP\n" +
                "qwl/kV3sMIGSBgNVHSMEgYowgYeAFPYyX6D7RjCeduMav9Kl1qSskk8KoWukaTBn\n" +
                "MQswCQYDVQQGEwJVUzELMAkGA1UECAwCQ0ExEjAQBgNVBAcMCVN1bm55dmFsZTEO\n" +
                "MAwGA1UECgwFQXJyaXMxFjAUBgNVBAsMDVJ1Y2t1c05ldHdvcmsxDzANBgNVBAMM\n" +
                "BnJvb3RDQYICEAAwNQYIKwYBBQUHAQEEKTAnMCUGCCsGAQUFBzABhhlodHRwOi8v\n" +
                "MTAuMjA2LjY2LjIyNDoyNTYwMA4GA1UdDwEB/wQEAwIFoDATBgNVHSUEDDAKBggr\n" +
                "BgEFBQcDATAYBgNVHREEETAPgg1zei5ydWNrdXMuY29tMA0GCSqGSIb3DQEBCwUA\n" +
                "A4IBAQApoIOlVf3qppKfXiGlInyQEOYxacw4r0OycXPtTffGrP4Fu4STF8Qmq9VC\n" +
                "FsOmvzHNFpn3DRWh83zPwT9ukvD7pwui1DYynoFU9/8iKvORvV4Xh/W7ypInvXRR\n" +
                "wwroQDgVInN1b/CRcL1rabRArYe1JIrbUe3zCPGzWJvkXH/vmRANG9magVrJeJ15\n" +
                "iEjJBeYtGhpYp0htJeIgJB4ABbDEKQILrP1l5aigwmtkPWc3lhXOE3FmCjtKCS8A\n" +
                "lCPEkN/b/6qXEYrM1wi8aWaTV9FZa6z14VD2vAKrClTpvgxPkk1XoKDZPqv1URE+\n" +
                "JubQ+WS1Gd9sG1Cpjw51GZSZFBmo\n" +
                "-----END CERTIFICATE-----";
//        String davidCertPem = "-----BEGIN CERTIFICATE-----\n" +
//                "MIID2DCCAsCgAwIBAgIBBjANBgkqhkiG9w0BAQsFADCBkjELMAkGA1UEBhMCVVMx\n" +
//                "CzAJBgNVBAgMAkNBMRIwEAYDVQQHDAlTdW5ueXZhbGUxEjAQBgNVBAoMCUNvbW1z\n" +
//                "Y29wZTEmMCQGCSqGSIb3DQEJARYXZGF2aWQubGVlQGNvbW1zY29wZS5jb20xJjAk\n" +
//                "BgNVBAMMHUZyZWVyYWQgQ2VydGlmaWNhdGUgQXV0aG9yaXR5MB4XDTIwMDkyMzAw\n" +
//                "MTkyMloXDTIxMDkyMzAwMTkyMlowezELMAkGA1UEBhMCVVMxCzAJBgNVBAgMAkNB\n" +
//                "MRIwEAYDVQQKDAlDb21tc2NvcGUxIzAhBgNVBAMMGkZyZWVyYWQgU2VydmVyIENl\n" +
//                "cnRpZmljYXRlMSYwJAYJKoZIhvcNAQkBFhdkYXZpZC5sZWVAY29tbXNjb3BlLmNv\n" +
//                "bTCCASIwDQYJKoZIhvcNAQEBBQADggEPADCCAQoCggEBAMiSNMWB9SY87P9Pi+Es\n" +
//                "j+UDjMYX7EVt/a3ykoJB6v6bDlQp5SI7kYqv5gyYRnaYmULldzX/8c1V9LzqD8Td\n" +
//                "IU2D9+MEyjuY3yuDcu/+FfiBXbiMEnwJ3XQelB9MewvMtLOY9sQYsHA2ymPoM+ZX\n" +
//                "A3ggCjOf6kfnMch3KhrlVRpH6nj5ZkariLpfcTF9VJxGRVbGhAmsX3rDp3OgpePV\n" +
//                "1B6EPcoCQIhhUxkk9xLcMtSHQMn0IjoNr4ZNi+K65bwzjFZX3endI/gqQlx9g3/X\n" +
//                "MqjEFcAR3MJM9xhZqR9fn5Vm6pY8meOAfngthN1yKA7rOERVk5Wf5zWmb+8wD2pN\n" +
//                "gBcCAwEAAaNPME0wEwYDVR0lBAwwCgYIKwYBBQUHAwEwNgYDVR0fBC8wLTAroCmg\n" +
//                "J4YlaHR0cDovL3d3dy5leGFtcGxlLmNvbS9leGFtcGxlX2NhLmNybDANBgkqhkiG\n" +
//                "9w0BAQsFAAOCAQEAJLYdTlmkCO9DOLYoUqW5RwgKPb1CMM+6Q14fIfmx5OmqJDq2\n" +
//                "XlKtXf64oKa7gSVVKCAWvqOprymdWXdE088KrAVsruEHxwnbbICwPjH/gCyxjhum\n" +
//                "PaeUC/uzaoGLm9B3RKWvJP7SzqjVmFQ58L48dxl9C+UrEh8M7gBifupbSzSrmk1B\n" +
//                "C+job+Iboj+Ml+IgHVMAb6adNtpWMe4rxlxTHINWW2+1W4fohHgogOhi7rehyIEd\n" +
//                "8gos8ulL6VguidMZL5Mfl6iNgFufAAreuYLRPw0kxpe4UwqzMV2Qze0r5ikTG0pj\n" +
//                "uV2QirtJa1XBqsGvwlch7qICsL9nVIy7dExGBg==\n" +
//                "-----END CERTIFICATE-----";
        BufferedReader br = new BufferedReader(new InputStreamReader(
                new ByteArrayInputStream(privateKeyPemData.getBytes())));

        PasswordFinder pFinder = getPasswordFinder(privateKeyPassword);
        PEMReader pemReader = new PEMReader(br, pFinder);

        KeyPair key = null;
        X509Certificate x509cert = null;
        File keyFile = null;
        File crtFile = null;
        try {
            x509cert = load(certPemData);
            key = (KeyPair) pemReader.readObject();
            System.out.println(ObjectUtils.equals(key.getPublic(), x509cert.getPublicKey()));
        } catch (Exception e) {
            if (key == null && x509cert != null) {
                try {
                    keyFile = File.createTempFile("testkey", ".key");
                    crtFile = File.createTempFile("testcrt", ".crt");
                    writeStringToFile(keyFile, privateKeyPemData);
                    writeStringToFile(crtFile, certPemData);
//                    return isKeyValid(
//                            crtFile.getAbsolutePath(),
//                            keyFile.getAbsolutePath());
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            System.out.println("false");
        } finally {
            try {
                pemReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (keyFile != null && keyFile.exists()) {
                keyFile.delete();
            }
            if (crtFile != null && crtFile.exists()) {
                crtFile.delete();
            }
        }
    }
}

