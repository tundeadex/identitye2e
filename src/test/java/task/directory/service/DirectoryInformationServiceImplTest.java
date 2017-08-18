package task.directory.service;

import task.directory.model.FileModel;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.LinkedList;

import static org.junit.Assert.assertEquals;

public class DirectoryInformationServiceImplTest {
    private DirectoryInformationServiceImpl directoryInformationService;
    private String path;

    @Before
    public void setUp() throws Exception {
        directoryInformationService = new DirectoryInformationServiceImpl();
        ClassLoader classLoader = getClass().getClassLoader();
        path = new File(classLoader.getResource("files").getFile()).toString();
    }

    @Test
    public void shouldReturnAllFilesInDirectory() throws Exception {
        LinkedList<FileModel> allFiles = directoryInformationService.getAll(path);
        allFiles.forEach(System.out::println);
        assertEquals(10, allFiles.size());
    }

    @Test
    public void shouldReturnEmptyListForNonExistentDirectory() throws Exception {
        LinkedList<FileModel> allFiles = directoryInformationService.getAll(path + "/inexistent-path");
        assertEquals(0, allFiles.size());
    }

    @Test
    public void shouldBeAbleToGetFilesByExtension() throws Exception {
        LinkedList<FileModel> csvFiles = directoryInformationService.getFilesByExtension(path, "csv");
        assertEquals(3, csvFiles.size());
    }

}