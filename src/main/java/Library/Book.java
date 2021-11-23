package Library;

public class Book {
    public int id;
    public String title;
    public Book(int id, String title)
    {
        this.id = id;
        this.title = title;
    }
    public void setId(int id)
    {
        this.id = id;
    }
    public int getId()
    {
        return id;
    }
    public void setTitle(String title)
    {
        this.title = title;
    }
    public String getTitle()
    {
        return title;
    }
    @Override
    public String toString(){
        return title;
    }
}
