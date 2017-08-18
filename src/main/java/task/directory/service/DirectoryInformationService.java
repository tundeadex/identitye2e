package task.directory.service;

import task.directory.model.Cars;
import task.directory.model.FileModel;

import java.util.LinkedList;
import java.util.List;

public interface DirectoryInformationService {

    LinkedList<FileModel> getAll(String directory);

    LinkedList<FileModel> getFilesByExtension(String directory, String mimetype);

    List<Cars> getCarsFromCsv();
}
