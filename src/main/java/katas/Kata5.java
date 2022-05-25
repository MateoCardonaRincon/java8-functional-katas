package katas;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import model.BoxArt;
import model.Movie;
import model.MovieList;
import util.DataUtil;

import java.sql.SQLOutput;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

/*
    Goal: Retrieve the largest rating using reduce()
    DataSource: DataUtil.getMovies()
    Output: Double
*/
public class Kata5 {
    public static Double execute() {
        List<Movie> movies = DataUtil.getMovies();

        Supplier<NullPointerException> noMoviesFound = () -> {
            throw new NullPointerException("No movies found");
        };

        return movies.stream().reduce((priorMovie, currentMovie) ->
                        currentMovie.getRating() > priorMovie.getRating() ? currentMovie : priorMovie)
                .orElseThrow(noMoviesFound)
                .getRating();
    }
}
