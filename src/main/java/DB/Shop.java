package DB;

public class Shop {

    private DBdriver dBdriver;

    public Shop(DBdriver dBdriver){

        this.setDBdriver(dBdriver);

    }


    public DBdriver getDBdriver() {
        return dBdriver;
    }

    public void setDBdriver(DBdriver dBdriver) {
        this.dBdriver = dBdriver;
    }
}
