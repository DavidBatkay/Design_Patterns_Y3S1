import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.zip.GZIPOutputStream;
import java.util.zip.GZIPInputStream;

public class CompressDecorator extends BaseDataSourceDecorator {

    public CompressDecorator(DataSource wrappee) {
        super(wrappee);
    }

    @Override
    public void Write(String data) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            GZIPOutputStream gzip = new GZIPOutputStream(baos);
            gzip.write(data.getBytes());
            gzip.close();
            String compressedBase64 = java.util.Base64.getEncoder().encodeToString(baos.toByteArray());
            _wrappee.Write(compressedBase64);
        } catch (Exception e) {
            throw new RuntimeException("Compression error", e);
        }
    }

    @Override
    public String Read() {
        try {
            String compressedBase64 = _wrappee.Read();
            byte[] compressed = java.util.Base64.getDecoder().decode(compressedBase64);
            GZIPInputStream gzip = new GZIPInputStream(new ByteArrayInputStream(compressed));
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            byte[] buffer = new byte[256];
            int len;
            while ((len = gzip.read(buffer)) != -1) {
                baos.write(buffer, 0, len);
            }

            return baos.toString();
        } catch (Exception e) {
            throw new RuntimeException("Decompression error", e);
        }
    }
}