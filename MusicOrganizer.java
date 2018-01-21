import java.util.ArrayList;
import java.util.Iterator;

/**
 * A class to hold details of audio tracks.
 * Individual tracks may be played.
 * 
 * @author David J. Barnes and Michael KÃ¶lling
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
    // Indica si hay una reproducción en curso.
    private boolean estaReproduciendo;

    /**
     * Create a MusicOrganizer
     */
    public MusicOrganizer()
    {
        tracks = new ArrayList<Track>();
        player = new MusicPlayer();
        reader = new TrackReader();
        estaReproduciendo = false;
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
        if(estaReproduciendo == false) {
            if(indexValid(index)) {
                Track track = tracks.get(index);
                player.startPlaying(track.getFilename());
                System.out.println("Now playing: " + track.getArtist() + " - " + track.getTitle());
                track.incrementarUnaUnidadPlayCount();
                estaReproduciendo = true;
            }
        }
        else {
            System.out.println("ERROR: Ya hay una reproducción en curso, si quiere escuchar otra primero debe parar esta.");
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
        if(estaReproduciendo == false) {
            if(tracks.size() > 0) {
                player.startPlaying(tracks.get(0).getFilename());
                tracks.get(0).incrementarUnaUnidadPlayCount();
                estaReproduciendo = true;
            }
        }
        else {
            System.out.println("ERROR: Ya hay una reproducción en curso, si quiere escuchar otra primero debe parar esta.");
        }
    }

    /**
     * Stop the player.
     */
    public void stopPlaying()
    {
        player.stop();
        estaReproduciendo = false;
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
     * Enumerar todas las pistas que contengan la cadena de búsqueda.
     * @param searchString La cadena de búsqueda que hay que encontrar.
     */
    public void findInTitle(String searchString) {
        for(Track track : tracks) {
            String title = track.getTitle();
            if(title.contains(searchString)) {
                System.out.println(track.getDetails());
            }
        }
    }

    /**
     * Fija el album de la canción seleccionada.
     */
    public void fijarAlbum(int indice, String album) {
        if(indice >= 0 && indice < tracks.size()) {
            tracks.get(indice).fijarAlbum(album);
        }
    }

    /**
     * Muestra por pantalla si hay una canción en reproducción o no.
     */
    public void isPlaying() {        
        if(estaReproduciendo == true) {
            System.out.println("Hay una canción reproduciéndose en este momento.");                   
        }
        else {
            System.out.println("No hay ninguna canción reproduciéndose.");
        }
    }

    /**
     * Muestra por pantalla los detalles de todas las canciones 
     * almacenadas en el organizador usando un iterador.
     */
    public void listAllTrackWithIterator() {
        Iterator<Track> it = tracks.iterator();

        while(it.hasNext()) {
            System.out.println(it.next().getDetails());
        }
    }

    /**
     * Elimina las canciones que contengan el artista 
     * introducido por el usuario.
     */
    public void removeByArtist(String artista) {
        Iterator<Track> it = tracks.iterator();

        while(it.hasNext()) {
            if(it.next().getArtist().contains(artista)) {
                it.remove();
            }
        }
    }
}
