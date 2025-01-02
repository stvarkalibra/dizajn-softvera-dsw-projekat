package raf.draft.dsw.serializer;

import raf.draft.dsw.model.structures.Project;

import java.io.File;

public interface Serializer {
    Project loadProject(File file);
    void saveProject(Project project);
}
