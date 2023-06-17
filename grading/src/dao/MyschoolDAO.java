package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.sql.DataSource;
import javax.swing.text.html.HTMLDocument.HTMLReader.PreAction;

public class MyschoolDAO {
	
	
	DataSource ds;
	
	
	public void setDataSource(DataSource ds) {
		this.ds = ds;
	}
	
	
	
	
	public StudentDTO getStudent(String id) {

		Connection conn = null;
		
		StudentDTO stu = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT * FROM 학생 WHERE 학생번호 = ?";
		
		
		try {
			
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				stu = new StudentDTO();
				stu.setId(rs.getString("학생번호"));
				stu.setDept(rs.getString("소속학과"));
				stu.setName(rs.getString("학생이름"));
				stu.setGrade(rs.getInt("학년"));
				stu.setGender(rs.getString("성별"));
				stu.setAge(rs.getInt("나이"));
				stu.setPhone(rs.getString("전화번호"));
				stu.setAddress(rs.getString("주소"));
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try { if(rs != null) rs.close(); } catch(Exception e) { }
			try { if(pstmt != null) pstmt.close(); } catch(Exception e) { }
			try { if(conn != null) conn.close(); } catch(Exception e) { }
			
		}
		
		return stu;
	}
	
	
	
	
	public boolean isStudent(String id) {
		boolean res = false;
		
		StudentDTO stu = getStudent(id);
		
		if(stu != null) {
			res = true;
		}
		
		return res;
	}
	
	
	
	
	
	public ArrayList<StudentDTO> getStudentList() {
		
		ArrayList<StudentDTO> list = new ArrayList<>();
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT * FROM 유효학생";
		
		
		try {
			
			conn = ds.getConnection();
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				StudentDTO stu = new StudentDTO();
				
				stu.setId(rs.getString("학생번호"));
				stu.setDept(rs.getString("소속학과"));
				stu.setName(rs.getString("학생이름"));
				stu.setGrade(rs.getInt("학년"));
				stu.setGender(rs.getString("성별"));
				stu.setAge(rs.getInt("나이"));
				stu.setPhone(rs.getString("전화번호"));
				stu.setAddress(rs.getString("주소"));
				
				list.add(stu);
			}
			
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try { if(rs != null) rs.close(); } catch(Exception e) { }
			try { if(stmt != null) stmt.close(); } catch(Exception e) { }
			try { if(conn != null) conn.close(); } catch(Exception e) { }
			
		}
		
		
		return list;
	}
	
	
	
	
	
	public ArrayList<ActiveLectureDTO> getLectureList() {
		
		ArrayList<ActiveLectureDTO> list = new ArrayList<>();
		
		Statement stmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT * FROM 수강과목";
		
		Connection conn = null;
		
		
		try {
			
			conn = ds.getConnection();
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				ActiveLectureDTO lec = new ActiveLectureDTO();
				
				lec.setId(rs.getString("강의번호"));
				lec.setTitle(rs.getString("강의이름"));
				lec.setProf_name(rs.getString("교수이름"));
				lec.setClassroom(rs.getString("강의실"));
				lec.setCategory(rs.getString("이수구분"));
				
				list.add(lec);
			}
			
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try { if(rs != null) rs.close(); } catch(Exception e) { }
			try { if(stmt != null) stmt.close(); } catch(Exception e) { }
			try { if(conn != null) conn.close(); } catch(Exception e) { }
			
		}
		
		
		return list;
	}
	
	
	
	
	
	
	public ArrayList<GradeStudentDTO> getLectureStudents(String lecId) {
		
		ArrayList<GradeStudentDTO> list = new ArrayList<>();
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT * FROM 수강학생 WHERE 강의번호 = ?";
		
		Connection conn = null;
		
		try {
			
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, lecId);
			
			rs = pstmt.executeQuery();
			
			
			while(rs.next()) {
				GradeStudentDTO stu = new GradeStudentDTO();
				
				stu.setStudId(rs.getString("학생번호"));
				stu.setName(rs.getString("학생이름"));
				stu.setScore(rs.getInt("점수"));
				
				list.add(stu);
			}
			
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try { if(rs != null) rs.close(); } catch(Exception e) { }
			try { if(pstmt != null) pstmt.close(); } catch(Exception e) { }
			try { if(conn != null) conn.close(); } catch(Exception e) { }
			
		}
		
		
		return list;
	}
	
	
	
	public int updateScore(String lecId, String studId, int score) {
		int res = 0;
		
		PreparedStatement pstmt = null;
		String sql = "UPDATE 성적 SET 점수=? WHERE 강의번호=? AND 학생번호=?";
		
		Connection conn = null;
		
		try {
			
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, score);
			pstmt.setString(2, lecId);
			pstmt.setString(3, studId);
			
			res = pstmt.executeUpdate();
			
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try { if(pstmt != null) pstmt.close(); } catch(Exception e) { }
			try { if(conn != null) conn.close(); } catch(Exception e) { }
			
		}
		
		
		return res;
	}
	
	
	
	
	public ArrayList<ScoreDTO> getScoreList(String studId) {
		
		ArrayList<ScoreDTO> list = new ArrayList<>();
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT * FROM 개인성적표 WHERE 학생번호 = ?";

		
		Connection conn = null;
		
		try {
			
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, studId);
			
			rs = pstmt.executeQuery();
			
			
			while(rs.next()) {
				ScoreDTO sc = new ScoreDTO();
				
				sc.setStudId(rs.getString("학생번호"));
				sc.setLecId(rs.getString("강의번호"));
				sc.setLecTitle(rs.getString("강의이름"));
				sc.setProf_name(rs.getString("교수이름"));
				sc.setCategory(rs.getString("이수구분"));
				sc.setScore(rs.getInt("점수"));
				
				list.add(sc);
			}
				
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try { if(rs != null) rs.close(); } catch(Exception e) { }
			try { if(pstmt != null) pstmt.close(); } catch(Exception e) { }
			try { if(conn != null) conn.close(); } catch(Exception e) { }
			
		}
		
		return list;
	}
	
	

	
	
	public ReportDTO getReport(String studId) {
		
		ReportDTO rep = null;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT a.학생번호, a.학생이름, a.소속학과, b.총점, b.평균" + 
				"	FROM 학생 a" + 
				"	INNER JOIN 개인성적계산 b" + 
				"	ON a.학생번호 = b.학생번호" + 
				"	WHERE a.학생번호 = ?";
		

		Connection conn = null;
		
		try {
			
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, studId);
			
			rs = pstmt.executeQuery();
			
			
			while(rs.next()) {
				rep = new ReportDTO();

				rep.setId(rs.getString("학생번호"));
				rep.setName(rs.getString("학생이름"));
				rep.setDept(rs.getString("소속학과"));
				rep.setSum(rs.getInt("총점"));
				rep.setAvg(rs.getDouble("평균"));
				
				String fin = getFin(rs.getDouble("평균"));
				rep.setFin(fin);
			}
			
			
			ArrayList<ScoreDTO> scores = getScoreList(studId);
			rep.setScores(scores);
		
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try { if(rs != null) rs.close(); } catch(Exception e) { }
			try { if(pstmt != null) pstmt.close(); } catch(Exception e) { }
			try { if(conn != null) conn.close(); } catch(Exception e) { }
			
		}
		
		
		return rep;
	}
	
	

	
	
	
	public String getFin(double avg) {
		String alpha = null;
		
		CallableStatement cstmt = null;
		String sql = "CALL 학점구하기(?, ?)";
		
		Connection conn = null;
		
		try {
			
			conn = ds.getConnection();
			cstmt = conn.prepareCall(sql);
			cstmt.setDouble(1, avg);
			cstmt.registerOutParameter(2, java.sql.Types.VARCHAR);  
			
			cstmt.execute();
			
			alpha = cstmt.getString(2);
			
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try { if(cstmt != null) cstmt.close(); } catch(Exception e) { }
			try { if(conn != null) conn.close(); } catch(Exception e) { }
			
		}
		
		
		
		return alpha;
	}
	
	
	
}
