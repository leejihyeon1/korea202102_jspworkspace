package MybatisConfigManager;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MybatisConfigManager {
	private static MybatisConfigManager instance;
	SqlSessionFactory sqlSessionFactory;
	InputStream inputStream;
	
	private MybatisConfigManager() {
		String resource = "com/korea/study0622/model/mybatis/config.xml";
		try {
			inputStream = Resources.getResourceAsStream(resource);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static MybatisConfigManager getInstance() {
		if(instance==null) {
			instance=new MybatisConfigManager();
		}
		return instance;
	}
	
	public SqlSession getSession() {
		return sqlSessionFactory.openSession();
	}
	
	public void closeSession(SqlSession sqlSession) {
		sqlSession.close();
	}
}
