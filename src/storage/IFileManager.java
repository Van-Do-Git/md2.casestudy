package storage;

import java.util.List;

public interface IFileManager<E> {

    public List<E> readFile(String path);
    public void writeFile(List<E> e,String path);
}
