package regexp;

public class FirstLvlPath {

    public static void main(String[] args) {
        FirstLvlPath firstLvlPath = new FirstLvlPath();
        String[] input = {"/", ".", "../../", "/etc", "/bin/", "/tmp/erttyhh", "/data/logs/text.ext", "/usr/bin/", "usr/sbin/", "/opt/ruckuswireless/wsg/log/abcdertg.tar"};
        for(String s: input) {
            System.out.println("[" + s + "] matches? " + firstLvlPath.isValidDeletePath(s));
        }
    }

    private boolean isValidDeletePath(String toBeDeletePath) {
        if(toBeDeletePath.startsWith("/opt/ruckuswireless/wsg/log/") || toBeDeletePath.startsWith("/tmp/") ) {
            return true;
        }

        return false;
    }
}
