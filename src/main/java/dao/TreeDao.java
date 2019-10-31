package dao;

import models.Tree;

public interface TreeDao {
    //create
    void addTree(Tree tree);
    void userPlantTree(int userId, int treeId, String latitude, String longitude);

    //read

    //update

    //delete

}
