import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static com.codeborne.selenide.Selenide.*;

public class AutoReservationSpec {

    @Test
    public void main() {
        Properties config = getConfigProperties();

//        open("https://airrsv.net/choya-umetaiken-kamakura/calendar");  // こっち鎌倉店
        open("https://airrsv.net/choya-umetaiken-kyoto/calendar");  // 鎌倉店は人気なので京都店で確認する

        int dateIndex = getDateIndexOfApplicablePage(config.getProperty("preferred.date"));

        if (!exist(dateIndex)) {
            System.out.println("指定した日付が見つかりません！");
            return;
        }

        clickPreferredTime(dateIndex, config.getProperty("preferred.time"));

        sleep(1000*60*5);
    }

    private Properties getConfigProperties() {
        Properties config = new Properties();
        try (FileInputStream in = new FileInputStream("./src/test/resources/config.properties")) {
            config.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return config;
    }

    private int getDateIndexOfApplicablePage(String preferredDate) {
        int dateIndex = 0;
        // 2週間後までしか予約できないのでこんだけ回せば十分
        for (int i = 0; i < 2; i++) {
            dateIndex = getDateIndexOf(preferredDate);
            if (exist(dateIndex)) {
                continue;
            }
            $(".ctlListItem.listNext").click();
            sleep(500);
        }
        return dateIndex;
    }

    private int getDateIndexOf(String preferredDate) {
        return $$(".scheduleTbl.is-scheduleHeader.type-week > thead > tr > td > span")
                .texts()
                .indexOf(preferredDate);
    }

    private boolean exist(int dateIndex) {
        return dateIndex != -1;
    }

    private void clickPreferredTime(int dateIndex, String preferredTime) {
        $$("td.scheduleBodyCell.tdCell")
                .get(dateIndex)
                .find("div > ul:nth-child(2) > li > ul:nth-child(2)")
                .find(By.partialLinkText(preferredTime))
                .click();
    }
}
