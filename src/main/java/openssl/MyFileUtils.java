package openssl;

import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.GroupPrincipal;
import java.nio.file.attribute.PosixFileAttributeView;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.UserPrincipal;
import java.nio.file.attribute.UserPrincipalLookupService;
import java.util.HashSet;
import java.util.Set;

public class MyFileUtils extends org.apache.commons.io.FileUtils {

    private final static int READABLE = 4;
    private final static int WRITABLE = 2;
    private final static int EXECUTABLE = 1;

    public static boolean createOrOverwriteIfDifferent(File dest, String data, boolean force) throws IOException {
        if (!force && dest.exists()) {
            String _data = null;
            _data = org.apache.commons.io.FileUtils.readFileToString(dest);

            if (StringUtils.equals(_data.trim(), data.trim())) {
                return false;
            }
        }
        org.apache.commons.io.FileUtils.writeStringToFile(dest, data);
        return true;
    }

    public static void createFileIfNotExist(String absPath) throws IOException {
        Path fp = Paths.get(absPath);
        if (!Files.exists(fp)) {
            Path dir = fp.getParent();
            if (!Files.exists(dir)) {
                Files.createDirectories(dir);
            }
            Files.createFile(fp);
        }
    }

    public static void setFileOwnerAndGroup(String absPath, String ownerName, String groupName) throws IOException {
        Path fp = Paths.get(absPath);

        UserPrincipalLookupService service = fp.getFileSystem().getUserPrincipalLookupService();
        UserPrincipal owner = service.lookupPrincipalByName(ownerName);
        GroupPrincipal group = service.lookupPrincipalByGroupName(groupName);

        Files.setOwner(fp, owner);
        Files.getFileAttributeView(fp, PosixFileAttributeView.class).setGroup(group);
    }

    public static Path setFilePermissions(String absPath, int owner, int group, int others) throws IOException {
        Path fp = Paths.get(absPath);
        Set<PosixFilePermission> perms = new HashSet<>();
        if ((owner & READABLE) != 0) {
            perms.add(PosixFilePermission.OWNER_READ);
        }
        if ((group & READABLE) != 0) {
            perms.add(PosixFilePermission.GROUP_READ);
        }
        if ((others & READABLE) != 0) {
            perms.add(PosixFilePermission.OTHERS_READ);
        }
        if ((owner & WRITABLE) != 0) {
            perms.add(PosixFilePermission.OWNER_WRITE);
        }
        if ((group & WRITABLE) != 0) {
            perms.add(PosixFilePermission.GROUP_WRITE);
        }
        if ((others & WRITABLE) != 0) {
            perms.add(PosixFilePermission.OTHERS_WRITE);
        }
        if ((owner & EXECUTABLE) != 0) {
            perms.add(PosixFilePermission.OWNER_EXECUTE);
        }
        if ((group & EXECUTABLE) != 0) {
            perms.add(PosixFilePermission.GROUP_EXECUTE);
        }
        if ((others & EXECUTABLE) != 0) {
            perms.add(PosixFilePermission.OTHERS_EXECUTE);
        }
        return Files.setPosixFilePermissions(fp, perms);
    }
}
