package admin.serviceImpl;

import com.devunlimit.project.admin.domain.dao.AdminDAO;
import com.devunlimit.project.admin.service.AdminService;
import com.devunlimit.project.admin.service.serviceImpl.AdminServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AdminServiceImplTest {

  @Mock
  AdminDAO adminDAO;

  @InjectMocks
  AdminService adminService = new AdminServiceImpl();

  @Before
  public void setUp() {
    Mockito.when(adminService.checkAdmin("dudqls816")).thenReturn(1); //관리자일경우 1반환
    Mockito.when(adminService.checkAdmin("admin")).thenReturn(0); //아닐경우 0반환
    Mockito.when(adminService.modify_log("jyj")).thenReturn(1); //수정이 완료된경우 1반환
    Mockito.when(adminService.modify_log("")).thenReturn(0); //수정이 실패한경우 0반환
  }

  //접속한 사용자가 관리자인지 체크
  @Test
  public void checkAdmin() {
    Assert.assertEquals(1, adminService.checkAdmin("dudqls816"));
    Assert.assertEquals(0, adminService.checkAdmin("admin"));
  }

  //아이디로 로그 수정 요청 성공 여부 체크
  @Test
  public void modify_log() {
    Assert.assertEquals(1, adminService.modify_log("jyj"));
    Assert.assertEquals(0, adminService.modify_log(""));
  }



}
