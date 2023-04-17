package config;

public interface PetEndpoints {
    String PET = "/pet";
    String PET_BY_ID ="/pet/{petID}";
    String PET_BY_STATUS = "/pet/findByStatus";
}
