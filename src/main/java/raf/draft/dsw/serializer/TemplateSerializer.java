package raf.draft.dsw.serializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import raf.draft.dsw.model.structures.Project;
import raf.draft.dsw.model.structures.Room;

import java.io.File;
import java.io.IOException;

public class TemplateSerializer implements Serializer{
    private final ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public Project loadProject(File file) {
        return null;
    }

    @Override
    public void saveProject(Project project) {

    }
}
