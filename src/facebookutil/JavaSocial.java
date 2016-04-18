package facebookutil;

import java.util.ArrayList;
import java.util.List;
import facebookutil.applications.AppMap;
import facebookutil.login.LoginUser;
import facebookutil.login.LoginObject;
import facebookutil.user.IUser;
import facebookutil.user.User;

public class JavaSocial implements IJavaSocial {
    
    private List<IUser> myUsers;
    private HighScoreBoard myHighScores;
    private IUser activeUser;
    private AppMap myApps;
    
    public JavaSocial () {
        myUsers = loadUsers();
        myHighScores = new HighScoreBoard ();
        myApps = new AppMap();
        myApps.loginApps();
    }

    private List<IUser> loadUsers () {
        UserReader reader = new UserReader ();
        return reader.getUsers();
    }

    @Override
    public List<IUser> getUsers () {
        return new ArrayList<>(myUsers);
    }

    @Override
    public IUser getUserByEmail (String email) {
        for (IUser user: myUsers) {
            if (user.getUserEmail().equals(email)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public HighScoreBoard getHighScoreBoard () {
        return myHighScores;
    }

    @Override
    public void loginUser (SocialType type, LoginObject login) {
        IUser user = getUserByEmail(login.getEmail());
        if (user == null) {
            user = createNewUser(login.getEmail());
        } else {
            System.out.println("User exists");
        }
        user.login(type, login);
        activeUser = user;
    }
    
    @Override
    public void loginUser (SocialType type) {
        LoginUser login = type.getLogin();
        login.authenticate(this);
    }

    @Override
    public IUser createNewUser (String email) {
        System.out.println("Creating new User");
        IUser newUser = new User(email);
        myUsers.add(newUser);
        return newUser;
    }

    public IUser getActiveUser () {
        return activeUser;
    }
    
    public AppMap getApplications () {
        return myApps;
    }

    @Override
    public void logoutAll () {
        UserWriter writer = new UserWriter();
        writer.write(getUsers());
    }


}