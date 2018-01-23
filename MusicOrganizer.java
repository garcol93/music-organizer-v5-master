import java.util.ArrayList;
import java.util.Iterator;

/**
 * A class to hold details of audio tracks.
 * Individual tracks may be played.
 * 
 * @author David J. Barnes and Michael Kölling
 * @version 2011.07.31
 */
public class MusicOrganizer
{
    // An ArrayList for storing music tracks.
    private ArrayList<Track> tracks;
    // A player for the music tracks.
    private MusicPlayer player;
    // A reader that can read music files and load them as tracks.
    private TrackReader reader;
    //hay reproducciones en curso
    private boolean reproduciendo;

    /**
     * Create a MusicOrganizer
     */
    public MusicOrganizer()
    {
        tracks = new ArrayList<Track>();
        player = new MusicPlayer();
        reader = new TrackReader();
        reproduciendo = false;
        readLibrary("audio");
        System.out.println("Music library loaded. " + getNumberOfTracks() + " tracks.");
        System.out.println();
    }

    /**
     * Add a track file to the collection.
     * @param filename The file name of the track to be added.
     */
    public void addFile(String filename)
    {
        tracks.add(new Track(filename));
    }

    /**
     * Add a track to the collection.
     * @param track The track to be added.
     */
    public void addTrack(Track track)
    {
        tracks.add(track);
    }

    /**
     * Play a track in the collection.
     * @param index The index of the track to be played.
     */
    public void playTrack(int index)
    {
        if(reproduciendo)
        {    
            System.out.println("Hay una reproduccion en curso finalice la reproduccion");
        }
        else{
            if(indexValid(index)) {
                Track track = tracks.get(index);
                track.incrementPlayCount();
                reproduciendo = true;
                player.startPlaying(track.getFilename());
                System.out.println("Now playing: " + track.getArtist() + " - " + track.getTitle());
            }
        }
    }

    /**
     * Return the number of tracks in the collection.
     * @return The number of tracks in the collection.
     */
    public int getNumberOfTracks()
    {
        return tracks.size();
    }

    /**
     * List a track from the collection.
     * @param index The index of the track to be listed.
     */
    public void listTrack(int index)
    {
        System.out.print("Track " + index + ": ");
        Track track = tracks.get(index);
        System.out.println(track.getDetails());
    }

    /**
     * Show a list of all the tracks in the collection.
     */
    public void listAllTracks()
    {
        System.out.println("Track listing: ");

        for(Track track : tracks) {
            System.out.println(track.getDetails());
        }
        System.out.println();
    }

    /**
     * List all tracks by the given artist.
     * @param artist The artist's name.
     */
    public void listByArtist(String artist)
    {
        for(Track track : tracks) {
            if(track.getArtist().contains(artist)) {
                System.out.println(track.getDetails());
            }
        }
    }

    /**
     * Remove a track from the collection.
     * @param index The index of the track to be removed.
     */
    public void removeTrack(int index)
    {
        if(indexValid(index)) {
            tracks.remove(index);
        }
    }

    /**
     * Play the first track in the collection, if there is one.
     */
    public void playFirst()
    {
        if(reproduciendo)
        {    
            System.out.println("Hay una reproduccion en curso finalice la reproduccion");
        }
        else{
            if(tracks.size() > 0) {
                tracks.get(0).incrementPlayCount();
                reproduciendo = true;
                player.startPlaying(tracks.get(0).getFilename());
            }
        }
    }

    /**
     * Stop the player.
     */
    public void stopPlaying()
    {
        player.stop();
        reproduciendo = false;
    }

    /**
     * Determine whether the given index is valid for the collection.
     * Print an error message if it is not.
     * @param index The index to be checked.
     * @return true if the index is valid, false otherwise.
     */
    private boolean indexValid(int index)
    {
        // The return value.
        // Set according to whether the index is valid or not.
        boolean valid;

        if(index < 0) {
            System.out.println("Index cannot be negative: " + index);
            valid = false;
        }
        else if(index >= tracks.size()) {
            System.out.println("Index is too large: " + index);
            valid = false;
        }
        else {
            valid = true;
        }
        return valid;
    }

    private void readLibrary(String folderName)
    {
        ArrayList<Track> tempTracks = reader.readTracks(folderName, ".mp3");

        // Put all thetracks into the organizer.
        for(Track track : tempTracks) {
            addTrack(track);
        }
    }

    /**
     * Enumera todas las pistas que contengan la cadena de busqueda.
     * @param searchString La cadena de busqueda que hay que encontrar.
     */
    public void findInTitle(String searchString)
    {
        for (Track track : tracks)
        {
            String title = track.getTitle();
            if(title.contains(searchString))
            {
                System.out.println(track.getDetails());
            }
        }
    }

    /**
     * permite introducir genero
     */
    public void setGeneroOfTrack(int posicion , String tipoGenero)
    {
        if(posicion >= 0 && posicion< tracks.size())
        {
            tracks.get(posicion).setGenero(tipoGenero);
        }
    }

    /**
     * permite sabaer si hay reproducciones
     */
    public void hayReproducciones()
    {if(reproduciendo)
        {
            System.out.println("Hay reproducciones en curso");
        }
        else {
            System.out.println("No hay reproducciones en curso");
        }
    }

    /**
     * muestra los detalles de todos los tracks almacenados
     */
    public void listAllTrackWithIterator()
    {
        Iterator<Track> ite = tracks.iterator();
        while(ite.hasNext())
        {
            Track track = ite.next();
            System.out.println(track.getDetails());
        }
    }
}
