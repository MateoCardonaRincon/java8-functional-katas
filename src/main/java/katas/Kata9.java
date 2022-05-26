package katas;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import model.*;
import util.DataUtil;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
    Goal: Retrieve each video's id, title, middle interesting moment time, and smallest box art url
    DataSource: DataUtil.getMovies()
    Output: List of ImmutableMap.of("id", 5, "title", "some title", "time", new Date(), "url", "someUrl")
*/
public class Kata9 {
    public static List<Map> execute() {
        List<MovieList> movieLists = DataUtil.getMovieLists();

        Stream<List<Movie>> videoListStream = movieLists.stream().map(MovieList::getVideos);

        Supplier<NullPointerException> noBoxArtFound = () -> {
            throw new NullPointerException("No box-art found");
        };

        Function<List<InterestingMoment>, Date> middleInterestingMoment =
                moments -> moments.stream().filter(moment -> moment.getType().equals("Middle"))
                        .collect(Collectors.toList()).get(0)
                        .getTime();

        Function<List<BoxArt>, String> smallestBoxArtUrl = boxArts -> boxArts.stream()
                .reduce((priorboxart, currentBoxArt) ->
                        priorboxart.getHeight() * priorboxart.getWidth()
                                < currentBoxArt.getHeight() * currentBoxArt.getWidth()
                                ? priorboxart : currentBoxArt)
                .orElseThrow(noBoxArtFound)
                .getUrl();

        Stream<ImmutableMap<String, Object>> immutableMapStream = videoListStream.flatMap(videos -> videos.stream()
                .map(video -> ImmutableMap.of(
                        "id", video.getId(),
                        "title", video.getTitle(),
                        "time", middleInterestingMoment.apply(video.getInterestingMoments()),
                        "url", smallestBoxArtUrl.apply(video.getBoxarts())
                )));

        return immutableMapStream.collect(Collectors.toList());
    }
}
