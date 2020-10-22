import java.nio.charset.StandardCharsets;
import java.util.Random;

public class Scripts {

    private static final char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();

    public static String bytesToHex(byte[] bytes) {
        byte[] hexChars = new byte[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = (bytes[j] & 0xFF);
            hexChars[j * 2] = (byte) HEX_ARRAY[v >>> 4];
            hexChars[j * 2 + 1] = (byte) HEX_ARRAY[v & 0x0F];
        }
        return new String(hexChars, StandardCharsets.UTF_8);
    }

    public static void main(String[] args) {
        final Random random = new Random();
        byte[] ID = new byte[2];
        random.nextBytes(ID);
        System.out.println(bytesToHex(ID));

        //Concatenating hex vals
        int transactionID = ((((ID[0] & 0xFF) << 8) + (ID[1] & 0xFF))) & 0xFFFF;
        System.out.printf("%x\n", transactionID);


        //Extract the 6th bit, this int should be 1 or 0 depending on if 6th bit is 1 or 0
        int AA = (ID[1] >>> 2) & 0x1;
        System.out.printf("%d\n", AA);


        //int ansCount = (((response[6] & oxFF) << 8) + (response[7] & 0xFF)) & 0xFFFF;

        //Checking if leading two bits of a byte are 11
        int firstTag = ID[0] & 0xFF;
        if ((firstTag >>> 6) == 3) {
            System.out.printf("%x\n", firstTag);
        }

        //Extracting bottom 14bits
        int select = ((((ID[0] & 0xFF) << 8) + (ID[1] & 0xFF))) & 0x3FFF;
        System.out.printf("%x\n", select);
    }
}
