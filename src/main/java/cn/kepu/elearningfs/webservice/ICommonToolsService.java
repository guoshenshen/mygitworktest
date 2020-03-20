package cn.kepu.elearningfs.webservice;

import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * 〈<br>
 * 〈〉
 *
 * @author lxx
 * @create 2019/7/30 17:26
 */
@WebService
public interface ICommonToolsService {
    /**
     * elearing-fs的WebService方法：
     * 移动课件资源的位置
     * @param oldPath
     * @param newPath
     * @return
     */
    boolean moveFolder(String oldPath, String newPath);

}
