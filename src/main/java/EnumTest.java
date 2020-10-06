public class EnumTest {

    public enum AuthMode {

        DOMAIN("Domain"),
        APZONE("AP Zone"),
        AP("Access Point"),
        PLANE("Plane"),
        SSID("SSID"),
        RADIO("Radio"),
        GGSN("GGSN"),
        SWITCH("Switch");

        private String text;

        AuthMode(String text) {
            this.text = text;
        }
//
//        @Override
//        public String toString() {
//            return this.text;
//        }
    }

    public enum AuthMode2 {

        DOMAIN,
        APZONE,
        AP,
        PLANE,
        SSID,
        RADIO,
        GGSN,
        SWITCH;
    }

    public static void main(String[] args) {
        System.out.println(AuthMode.APZONE.text);
        System.out.println(AuthMode.APZONE.name());
        System.out.println(AuthMode2.APZONE.name());
    }
    
}
