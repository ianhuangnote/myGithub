import java.util.StringJoiner;

public class StringHandler {

    public static void main(String[] args) {
        StringJoiner resultLineJointer = new StringJoiner("\n","policy: ", "123");
        resultLineJointer.add("Generating CSR via Openssl...");
        resultLineJointer.add("- To be used temp key path: " + "/tmp/csr/tempCsr.key");
        resultLineJointer.add("- To be output CSR: /tmp/csr/tmpCsr.csr");
        resultLineJointer.setEmptyValue("5555");
//        System.out.println(resultLineJointer.toString());

        StringJoiner resultLineJointer2 = new StringJoiner(" ");
        resultLineJointer2.add("\"ssh-rsa AAAAB3NzaC1yc2EAAAABJQAAAQEAq2EgIjCXnR6d3Emj6UWaGViQks/zSD6gW7rJ8WLFr+mgBECG1T62QtOrvSVpeKyPG0yBPndMkW0IRSCxoYyE/o/ZF7K7p6mu84puSUMY+GcgjAkC5HPeho8pShEliczBPs9wxFnDY+83NaKkcYYfdYKnLs25UIO9prIkz8W2aMyOF4gEawIivXR3qnNDgUGkWb+YXAPKAGwoNrGeX/R9LwnDwv57NGVc73Mz67gCa+oWCZgOAumg2PI0bXQywc87oyUrNmJCTDmjIJ+GkuLoHdaxT3CpCUsvDtBlzbbBSLHKTTmOu5oQbx9ZDTf95zqR75LGCwF8QefQDtAwZm/0BQ== rsa-key-20200525\"");
//        resultLineJointer2.add("--");
        resultLineJointer2.add("\"ssh-rsa2 AAAAB3NzaC1yc2EAAAABJQAAAQEAq2EgIjCXnR6d3Emj6UWaGViQks/zSD6gW7rJ8WLFr+mgBECG1T62QtOrvSVpeKyPG0yBPndMkW0IRSCxoYyE/o/ZF7K7p6mu84puSUMY+GcgjAkC5HPeho8pShEliczBPs9wxFnDY+83NaKkcYYfdYKnLs25UIO9prIkz8W2aMyOF4gEawIivXR3qnNDgUGkWb+YXAPKAGwoNrGeX/R9LwnDwv57NGVc73Mz67gCa+oWCZgOAumg2PI0bXQywc87oyUrNmJCTDmjIJ+GkuLoHdaxT3CpCUsvDtBlzbbBSLHKTTmOu5oQbx9ZDTf95zqR75LGCwF8QefQDtAwZm/0BQ== rsa-key-20200526\"");
        System.out.println(resultLineJointer2.toString());
    }
}
