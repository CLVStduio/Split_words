package cn.clvstudio.webcrawling;

/**
 * 应用宝评论模板
 * @author Darnell
 *
 */
public class CommentMyapp {
	
	/**
	 * 评论内容
	 */
	private String content;
	/**
	 * ？？？
	 */
	private String pkgName;
	/**
	 * 版本号
	 */
	private String versionCode;
	/**
	 * 评论创建时间
	 */
	private String createdTime;
	/**
	 * 评分
	 */
	private String score;
	/**
	 * 用户唯一识别码（腾讯内部？）
	 */
	private String uin;
	/**
	 * 用户昵称
	 */
	private String nickName;
	/**
	 * 手机型号
	 */
	private String phoneMode;
	/**
	 * 手机品牌
	 */
	private String phoneBrand;
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getPkgName() {
		return pkgName;
	}
	public void setPkgName(String pkgName) {
		this.pkgName = pkgName;
	}
	public String getVersionCode() {
		return versionCode;
	}
	public void setVersionCode(String versionCode) {
		this.versionCode = versionCode;
	}
	public String getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	public String getUin() {
		return uin;
	}
	public void setUin(String uin) {
		this.uin = uin;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getPhoneMode() {
		return phoneMode;
	}
	public void setPhoneMode(String phoneMode) {
		this.phoneMode = phoneMode;
	}
	public String getPhoneBrand() {
		return phoneBrand;
	}
	public void setPhoneBrand(String phoneBrand) {
		this.phoneBrand = phoneBrand;
	}
	@Override
	public String toString() {
		return "CommentMyapp [content=" + content + ", pkgName=" + pkgName + ", versionCode=" + versionCode
				+ ", createdTime=" + createdTime + ", score=" + score + ", uin=" + uin + ", nickName=" + nickName
				+ ", phoneMode=" + phoneMode + ", phoneBrand=" + phoneBrand + "]";
	}
	
	
}
