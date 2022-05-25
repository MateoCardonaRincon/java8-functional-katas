package katas;

import model.BoxArt;
import model.Movie;
import util.DataUtil;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
    Goal: Retrieve the url of the largest boxart using map() and reduce()
    DataSource: DataUtil.getMovieLists()
    Output: String
*/
public class Kata6 {
    public static String execute() {
        List<Movie> movies = DataUtil.getMovies();

        Supplier<NullPointerException> noBoxArtFound = () -> {
            throw new NullPointerException("No box-art found");
        };

        Stream<BoxArt> boxArtStream = movies.stream().flatMap(movie -> movie.getBoxarts().stream());

        return boxArtStream.reduce((priorBoxart, currentBoxArt) ->
                        priorBoxart.getWidth() * priorBoxart.getHeight() > currentBoxArt.getWidth() * currentBoxArt.getHeight() ?
                                priorBoxart : currentBoxArt)
                .orElseThrow(noBoxArtFound)
                .getUrl();
    }
}
