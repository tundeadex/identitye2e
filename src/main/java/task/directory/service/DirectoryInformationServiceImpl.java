package task.directory.service;

import task.directory.model.Cars;
import task.directory.model.FileModel;
import org.apache.commons.io.FilenameUtils;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;
//import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.pmw.tinylog.Logger;

public class DirectoryInformationServiceImpl implements DirectoryInformationService {

    public LinkedList<FileModel> getAll(String directoryPath) {
        LinkedList<FileModel> list = new LinkedList<>();
        try {
            Files.list(Paths.get(directoryPath))
                    .forEach((path) -> {
                        FileModel model = new FileModel();
                        try {
                            String mimetype = Files.probeContentType(path);
                            if (mimetype == null) {
                                mimetype = "";
                            }
                            model.setMimetype(mimetype);
                            model.setSize( Files.size(path) );
                            model.setName( path.getFileName().toString() );
                            String extension = FilenameUtils.getExtension(path.getFileName().toString());
                            model.setExtension(extension);
                            list.add(model);
                        } catch (IOException e) {
                            Logger.error(e.getLocalizedMessage());
                        }
                    });
        } catch (IOException e) {
            Logger.error(e.getLocalizedMessage());
        }
        return list;
    }

    public LinkedList<FileModel> getFilesByExtension(String directory, String extension) {
        return getAll(directory)
                .stream().filter((model) -> model.getExtension().equals(extension))
                .collect(Collectors.toCollection(LinkedList::new));
    }

    public List<Cars> getCarsFromCsv() {
        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource("files/list-of-cars.csv");
        File file = new File(resource.getFile());
        List<Cars> cars = new ArrayList<>();
        try {
            InputStream is = new FileInputStream(file);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            cars = br.lines()
                    .map(mapToCar)
                    .collect(Collectors.toList());
        } catch (FileNotFoundException e) {
            Logger.error(e.getLocalizedMessage());
        }
        return cars;
    }


    private static Function<String, Cars> mapToCar = (line) -> {
        String[] splitString = line.split(",");
        return new Cars(splitString[0], (splitString[1]), splitString[2]);
    };
}
