package use_case.delete_account;

public interface DeleteAccountUserDataInterface {
    boolean existsByName(String username);
    void deleteByName(String username);
}
