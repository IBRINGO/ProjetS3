package com.JAVA.DAO;

import java.util.List;

import com.JAVA.BEAN.Candidat;
import com.JAVA.BEAN.Experience;

public interface ExperienceDao {

	void addExperience(Experience experience);
    void updateExperience(Experience experience);
    void deleteExperience(int idExperience);
    Experience findExperienceParId(int idExperience);
    List<Experience> listExperiences();	
	
}
