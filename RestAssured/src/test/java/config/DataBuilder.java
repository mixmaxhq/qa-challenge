package config;

import pojo.Category;
import pojo.Pet;
import pojo.Tags;

import java.util.ArrayList;
import java.util.List;

public class DataBuilder {

    private int petID = 101777;
    private String petName = "Eeyore";
    private String petStatus = "Available";
    private int petTagId = 1;
    private String petTagName = "Brown";
    private int petCategoryId = 2;
    private String petCategoryName = "Dogs";
    private String petPhotoUrl = "https://photos.google.com/u/9/photo/AF1QipOlaI5ea27ov_qnKp-6m-Wp3XBwXGUENElr1-A9";

    public Pet buildPet(){

        Pet pet = new Pet();
        pet.setId(petID);
        pet.setName(petName);
        pet.setStatus(petStatus);

        Tags petTag = new Tags();
        petTag.setId(petTagId);
        petTag.setName(petTagName);

        List<Tags> tagsList = new ArrayList<Tags>();
        tagsList.add(petTag);
        pet.setTags(tagsList);

        Category category = new Category();
        category.setId(petCategoryId);
        category.setName(petCategoryName);
        pet.setCategory(category);

        List<String> petPhotoList = new ArrayList<String>();
        petPhotoList.add(petPhotoUrl);
        pet.setPhotoUrls(petPhotoList);

        return pet;
    }
}
