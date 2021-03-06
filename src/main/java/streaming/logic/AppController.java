package streaming.logic;

import streaming.logic.entities.Media;
import streaming.logic.entities.User;
import streaming.logic.exceptions.EmailAlreadyExistException;
import streaming.logic.exceptions.NoSuchUserException;
import streaming.logic.exceptions.UserListException;
import streaming.logic.interfaces.MediaMapper;
import streaming.logic.interfaces.UserMapper;

import java.util.List;

public class AppController {

    private UserMapper userMapper;
    private MediaMapper mediaMapper;

    public AppController(UserMapper userMapper, MediaMapper mediaMapper) {
        this.userMapper = userMapper;
        this.mediaMapper = mediaMapper;
    }

    public boolean registerUser(User user) {
        try {
            userMapper.saveUser(user);
        } catch (EmailAlreadyExistException e) {
            return false;
        }
        return true;
    }

    public boolean validateLogin(String username, String password) {
        boolean success = false;
        try {
            User user = userMapper.getUser(username);
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                success = true;
            }
        } catch (NoSuchUserException e) {
            return false;
        }

        return success;
    }

    public List<Media> fetchAll(String media) {
        List<Media> result;

        if (media.equalsIgnoreCase("all")) {
            result = mediaMapper.getAll();
        } else if (media.equalsIgnoreCase("movies")) {
            result = mediaMapper.getMovies();
        } else {
            result = mediaMapper.getSeries();
        }


        return result;
    }

    public List<Media> fetchAllFromGenre(String genre, String media) {
        List<Media> result;

        result = mediaMapper.getAllFromGenre(genre, media);

        return result;
    }

    public List<Media> fetchRatingOver(double rating, String media) {
        List<Media> result;

        result = mediaMapper.getByRating(rating, media);

        return result;
    }

    public List<Media> fetchByName(String name, String media) {
        List<Media> result;

        result = mediaMapper.getByName(name, media);

        return result;
    }

    public List<Media> fetchReleaseAfter(int release, String media) {
        List<Media> result = null;

        result = mediaMapper.getByRelease(release, media);

        return result;
    }

    public List<Media> fetchUserList(String username) {
        List<Media> result;

        result = mediaMapper.getUserList(username);

        return result;
    }

    public boolean addToUserList(String username, String media, String mediaTitle) {
        boolean success = false;
        try {
            userMapper.addToUserList(username, media, mediaTitle);
            success = true;

        } catch (UserListException e) {
            return success;
        }

        return success;
    }

    public boolean removeFromUserList(String username, String media, String mediaTitle) {
        boolean success = false;
        try {
            userMapper.removeFromUserList(username, media, mediaTitle);
            success = true;

        } catch (UserListException e) {
            return success;
        }
        return success;
    }
}