import java.util.Base64;

public class EncryptionDecorator extends BaseDataSourceDecorator {

    public EncryptionDecorator(DataSource wrappee) {
        super(wrappee);
    }

    @Override
    public void Write(String data) {
        String encrypted = Base64.getEncoder().encodeToString(data.getBytes());
        _wrappee.Write(encrypted);
    }

    @Override
    public String Read() {
        String encrypted = _wrappee.Read();
        byte[] decoded = Base64.getDecoder().decode(encrypted);
        return new String(decoded);
    }
}