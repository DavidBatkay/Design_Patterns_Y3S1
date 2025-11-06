public class Main {
    public static void main(String[] args) {

        DataSource ds =
                new CompressDecorator(
                        new EncryptionDecorator(
                                new StorageDataSource()
                        )
                );

        ds.Write("TEST1 TEST2 TEST3.");
        System.out.println("Message:: " + ds.Read());
    }
}