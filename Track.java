/**
 * Store the details of a music track,
 * such as the artist, title, and file name.
 * 
 * @author David J. Barnes and Michael Kölling
 * @version 2011.07.31
 */
public class Track
{
    // The artist.
    private String artist;
    // The track's title.
    private String title;
    // Where the track is stored.
    private String filename;
    // Contabiliza el n�mero de reproducciones de las canciones.
    private int playCount;
    // El album al que pertenecen las canciones.
    private String album;

    /**
     * Constructor for objects of class Track.
     * @param artist The track's artist.
     * @param title The track's title.
     * @param filename The track file. 
     */
    public Track(String artist, String title, String filename)
    {
        setDetails(artist, title, filename);
        playCount = 0;
        album = "";
    }

    /**
     * Constructor for objects of class Track.
     * It is assumed that the file name cannot be
     * decoded to extract artist and title details.
     * @param filename The track file. 
     */
    public Track(String filename)
    {
        setDetails("unknown", "unknown", filename);
        playCount = 0;
        album = "";
    }

    /**
     * Return the artist.
     * @return The artist.
     */
    public String getArtist()
    {
        return artist;
    }

    /**
     * Return the title.
     * @return The title.
     */
    public String getTitle()
    {
        return title;
    }

    /**
     * Return the file name.
     * @return The file name.
     */
    public String getFilename()
    {
        return filename;
    }

    /**
     * Return details of the track: artist, title and file name.
     * @return The track's details.
     */
    public String getDetails()
    {
        return artist + ": " + title + "  (file: " + filename + ")" + " - Reproducciones: " + playCount + " - Album: " + album;
    }

    /**
     * Set details of the track.
     * @param artist The track's artist.
     * @param title The track's title.
     * @param filename The track file. 
     */
    private void setDetails(String artist, String title, String filename)
    {
        this.artist = artist;
        this.title = title;
        this.filename = filename;
    }

    /**
     * Incrementa 1 unidad el valor de playCount.
     */
    public void incrementarUnaUnidadPlayCount() {
        playCount += 1;
    }

    /**
     * Reinicializa a 0 el valor de playCount.
     */
    public void reinicializarPlayCount() {
        playCount = 0;
    }
    
    /**
     * Devuelve el album.
     */
    public String obtenerAlbum() {
        return album;
    }
    
    /**
     * Fija el album.
     */
    public void fijarAlbum(String album) {
        this.album = album;
    }
}
