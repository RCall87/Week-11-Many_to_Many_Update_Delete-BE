import java.util.List;
import java.util.NoSuchElementException;
import projects.dao.ProjectDao;
import projects.entity.Project;
import projects.exception.DbException;

/**
 * This class implements the service layer in the 3-tier application. The CRUD operations that the
 * application performs are so simple that this class acts mostly as a pass-through from the input
 * layer to the data layer.
 * 
 * @author Promineo
 *
 */
public class ProjectService {
  private ProjectDao projectDao = new ProjectDao();

  /**
   * This method simply calls the DAO class to insert a project row.
   * 
   * @param project The {@link Project} object.
   * @return The Project object with the newly generated primary key value.
   */
  public Project addProject(Project project) {
    return projectDao.insertProject(project);
  }

  /**
   * This method calls the project DAO to retrieve all project rows without accompanying details
   * (materials, steps, and categories).
   * 
   * @return A list of project records.
   */
  public List<Project> fetchAllProjects() {
    return projectDao.fetchAllProjects();
  }

  /**
   * This method calls the project DAO to get all project details, including materials, steps, and
   * categories. If the project ID is invalid, it throws an exception.
   * 
   * @param projectId The project ID.
   * @return A Project object if successful.
   * @throws NoSuchElementException Thrown if the project with the given ID does not exist.
   */
  public Project fetchProjectById(Integer projectId) {
    return projectDao.fetchProjectById(projectId).orElseThrow(() -> new NoSuchElementException(
        "Project with project ID=" + projectId + " does not exist."));
  }

  /**
   * This method updates the project details using the provided Project object.
   * 
   * @param project The updated Project object.
   * @throws DbException Thrown if there's an issue updating the project details.
   */
  public void modifyProjectDetails(Project project) throws DbException {
    boolean updateSuccessful = projectDao.modifyProjectDetails(project);
    
    if (!updateSuccessful) {
      throw new DbException("The project does not exist.");
    }
  }
}
