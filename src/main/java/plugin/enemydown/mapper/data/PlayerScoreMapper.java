package plugin.enemydown.mapper.data;

import java.util.List;
import org.apache.ibatis.annotations.Select;
import plugin.enemydown.mapper.data.data.PlayerScore;

public interface PlayerScoreMapper {

  @Select("SELECT * FROM player_score")
  List<PlayerScore> selectList();
}
