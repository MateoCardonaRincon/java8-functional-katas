package katas;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import model.Bookmark;
import model.BoxArt;
import model.Movie;
import model.MovieList;
import util.DataUtil;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
    Goal: Retrieve the id, title, and smallest box art url for every video
    DataSource: DataUtil.getMovieLists()
    Output: List of ImmutableMap.of("id", "5", "title", "Bad Boys", "boxart": "url)
*/
public class Kata7 {
    public static List<Map> execute() {
        List<MovieList> movieLists = DataUtil.getMovieLists();

        Supplier<NullPointerException> noBoxArtFound = () -> {
            throw new NullPointerException("No box-art found");
        };

        Function<List<BoxArt>, String> smallestBoxArtUrl = boxArts -> boxArts.stream()
                .reduce((priorboxart, currentBoxArt) ->
                        priorboxart.getHeight() * priorboxart.getWidth()
                                < currentBoxArt.getHeight() * currentBoxArt.getWidth()
                                ? priorboxart : currentBoxArt)
                .orElseThrow(noBoxArtFound)
                .getUrl();

        Stream<List<Movie>> movieListStream = movieLists.stream().map(MovieList::getVideos);

        Stream<ImmutableMap<String, Object>> immutableMapStream = movieListStream.flatMap(movies ->movies.stream()
                .map(movie -> ImmutableMap.of(
                        "id", movie.getId(),
                        "title", movie.getTitle(),
                        "boxart", smallestBoxArtUrl.apply(movie.getBoxarts())
                )));

        return immutableMapStream.collect(Collectors.toList());
    }
}
