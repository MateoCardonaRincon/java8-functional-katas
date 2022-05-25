package katas;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import model.BoxArt;
import model.Movie;
import model.MovieList;
import util.DataUtil;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
    Goal: Retrieve id, title, and a 150x200 box art url for every video
    DataSource: DataUtil.getMovieLists()
    Output: List of ImmutableMap.of("id", "5", "title", "Bad Boys", "boxart": BoxArt)
*/
public class Kata4 {
    public static List<Map> execute() {
        List<MovieList> movieLists = DataUtil.getMovieLists();

        Predicate<BoxArt> matchRequiredDimensions = boxArt -> boxArt.getHeight() == 200 && boxArt.getWidth() == 150;

        Function<List<BoxArt>, List<BoxArt>> filterBoxArts = boxArts -> boxArts.stream()
                .filter(matchRequiredDimensions)
                .collect(Collectors.toList());

        Stream<List<Movie>> videoListStream = movieLists.stream().map(MovieList::getVideos);

        Stream<ImmutableMap<String, Object>> immutableMapStream = videoListStream.flatMap(movies -> movies.stream()
                .map(movie -> ImmutableMap.of(
                        "id", movie.getId(),
                        "title", movie.getTitle(),
                        "boxart", filterBoxArts.apply(movie.getBoxarts()).get(0)
                )));

        return immutableMapStream.collect(Collectors.toList());

    }
}
