package myhrm.dao.provider;

import myhrm.domain.Notice;
import org.apache.ibatis.jdbc.SQL;


import java.util.Map;



;


/**   
 * @Description: 公告动态SQL语句提供类
 * <br>网站：<a href="http://www.fkit.org">疯狂Java</a> 
 * @author 肖文吉	36750064@qq.com   
 * @version V1.0   
 */
public class NoticeDynaSqlProvider {
	// 分页动态查询
	public String selectWhitParam(final Map<String, Object> params){
		String sql =  new SQL(){
			{
				SELECT("*");
				FROM("notice_inf");
				if(params.get("notice") != null){
					Notice notice = (Notice)params.get("notice");
					if(notice.getTitle() != null && !notice.getTitle().equals("")){
						WHERE("  title LIKE CONCAT ('%',#{notice.title},'%') ");
					}
					if(notice.getContent() != null && !notice.getContent().equals("")){
						WHERE("  content LIKE CONCAT ('%',#{notice.content},'%') ");
					}
				}
			}
		}.toString();
		
		if(params.get("pageModel") != null){
			sql += " limit #{pageModel.firstLimitParam} , #{pageModel.pageSize}  ";
		}
		
		return sql;
	}	
	// 动态查询总数量
	public String count(final Map<String, Object> params){
		return new SQL(){
			{
				SELECT("count(*)");
				FROM("notice_inf");
				if(params.get("notice") != null){
					Notice notice = (Notice)params.get("notice");
					if(notice.getTitle() != null && !notice.getTitle().equals("")){
						WHERE("  title LIKE CONCAT ('%',#{notice.title},'%') ");
					}
					if(notice.getContent() != null && !notice.getContent().equals("")){
						WHERE("  content LIKE CONCAT ('%',#{notice.content},'%') ");
					}
				}
			}
		}.toString();
	}	
	// 动态插入
	public String insertNotice(final Notice notice){
		
		return new SQL(){
			{
				INSERT_INTO("notice_inf");
				if(notice.getTitle() != null && !notice.getTitle().equals("")){
					VALUES("title", "#{title}");
				}
				if(notice.getContent() != null && !notice.getContent().equals("")){
					VALUES("content", "#{content}");
				}
				if(notice.getUser() != null && notice.getUser().getId() != null){
					VALUES("user_id", "#{user.id}");
				}
			}
		}.toString();
	}
	// 动态更新
	public String updateNotice(final Notice notice){
		
		return new SQL(){
			{
				UPDATE("notice_inf");
				if(notice.getTitle() != null && !notice.getTitle().equals("")){
					SET(" title = #{title} ");
				}
				if(notice.getContent() != null && !notice.getContent().equals("")){
					SET(" content = #{content} ");
				}
				if(notice.getUser() != null && notice.getUser().getId() != null){
					SET(" user_id = #{user.id} ");
				}
				WHERE(" id = #{id} ");
			}
		}.toString();
	}
	
}