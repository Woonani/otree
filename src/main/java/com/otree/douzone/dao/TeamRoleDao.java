package com.otree.douzone.dao;

import java.sql.SQLException;
import java.util.List;

import com.otree.douzone.dto.TeamRole;
import com.otree.douzone.dto.Workspace;

public interface TeamRoleDao {
	public void insertTeamRole(TeamRole teamRole) throws SQLException;
//	public List<Workspace> selectWorkspaceList(int userId) throws SQLException;
//	public Workspace selectWorkspaceById(int workspaceId) throws SQLException;
//	public void updateWorkspace(int workspaceId, Workspace workspace) throws SQLException;
//	public int deleteWorkspace(int workspaceId) throws SQLException;
}