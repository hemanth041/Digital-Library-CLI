public class Book{
    private String Title;
    private String Author;
    private String isbn;
    private int Year;
    private boolean isAvailable;
    public Book(String Title,String Author,String isbn,int Year){
        this.Title=Title;
        this.Author=Author;
        this.isbn=isbn;
        this.Year=Year;
        this.isAvailable=true;
    }
    public String getTitle(){return Title;}
    public String getAuthor(){return Author;}
    public String getisbn(){return isbn;}
    public int getYear(){return Year;}
    public boolean isAvailable(){return isAvailable;}
    public void setAvailable(boolean available){isAvailable=available;}

    @Override
    public String toString(){
        return Title + "by" + Author +"(ISBN:"+isbn+ ")-"+(isAvailable?"Available":"Borrowed");
    }
}




