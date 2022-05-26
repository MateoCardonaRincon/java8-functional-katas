package katas;

import com.google.common.collect.ImmutableList;
import com.sun.jdi.PathSearchingVirtualMachine;
import model.Movie;
import model.MovieList;
import util.DataUtil;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
    Goal: Use map() and flatMap() to project and flatten the movieLists into an array of video ids (flatMap(c -> c.stream()))
    DataSource: DataUtil.getMovieLists()
    Output: List of Integers
*/
public class Kata3 {
    public static List<Integer> execute() {
        List<MovieList> movieLists = DataUtil.getMovieLists();

        Stream<List<Movie>> listOfMovies = movieLists.stream().map(movies -> movies.getVideos());

        Stream<Integer> videoIds = listOfMovies.flatMap(videos -> videos.stream().map(video -> video.getId()));

        return videoIds.collect(Collectors.toList());

    }
}
