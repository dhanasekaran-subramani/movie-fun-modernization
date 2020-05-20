package org.superbiz.moviefun.albumsapi;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestOperations;

import java.util.List;

public class AlbumsClient {

    private String albumsUrl;
    private RestOperations restOperations;

    public AlbumsClient(String albumsUrl, RestOperations restOperations) {
        this.albumsUrl = albumsUrl;
        this.restOperations = restOperations;
    }

    private static ParameterizedTypeReference<List<org.superbiz.moviefun.albumsapi.AlbumInfo>> albumListType = new ParameterizedTypeReference<List<org.superbiz.moviefun.albumsapi.AlbumInfo>>() {
    };

    public void addAlbum(org.superbiz.moviefun.albumsapi.AlbumInfo album) {
        restOperations.postForEntity(albumsUrl, album, org.superbiz.moviefun.albumsapi.AlbumInfo.class);
    }

    public org.superbiz.moviefun.albumsapi.AlbumInfo find(long id) {
        return restOperations.getForObject(albumsUrl + "/" + id, org.superbiz.moviefun.albumsapi.AlbumInfo.class);
    }

    public List<org.superbiz.moviefun.albumsapi.AlbumInfo> getAlbums() {
        return restOperations.exchange(albumsUrl, HttpMethod.GET, null, albumListType).getBody();
    }

    public void deleteAlbum(long id) {
        restOperations.delete(albumsUrl + "/" + id);
    }

    public void updateAlbum(org.superbiz.moviefun.albumsapi.AlbumInfo album) {
        restOperations.put(albumsUrl, album);
    }
}