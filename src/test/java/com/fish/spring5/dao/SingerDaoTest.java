package com.fish.spring5.dao;

import com.fish.spring5.config.AppConfig;
import com.fish.spring5.entity.Album;
import com.fish.spring5.entity.Instrument;
import com.fish.spring5.entity.Singer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import java.util.Arrays;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @Description:
 * @Author: Jayzou
 * @Date: 2018/11/7
 */
public class SingerDaoTest {

    private static final Logger logger = LoggerFactory.getLogger(SingerDaoTest.class);

    private GenericApplicationContext context;
    private SingerDao singerDao;

    @Before
    public void setUp() {
        context = new AnnotationConfigApplicationContext(AppConfig.class);
        singerDao = context.getBean(SingerDao.class);
        assertNotNull(singerDao);
    }

    @Test
    public void findAll() {
        List<Singer> singerList = singerDao.findAll();
        assertNotNull(singerList);
        listSingers(singerList);
    }


    @Test
    public void findAllWithAlbum() {
        List<Singer> singerList = singerDao.findAllWithAlbum();
        assertNotNull(singerList);
        listSingersWithAlbum(singerList);
    }

    @Test
    public void findById() {
        Singer singer = singerDao.findById(1L);
        assertNotNull(singer);
        listSingers(Arrays.asList(singer));
    }

    @Test
    public void save() {
        Singer singer = new Singer();
        singer.setFirstName("BB");
        singer.setLastName("King");
        singer.setBirthDate(new Date(
                (new GregorianCalendar(1940, 8, 16)).getTime().getTime()));

        Album album = new Album();
        album.setTitle("My Kind of Blues");
        album.setReleaseDate(new java.sql.Date(
                (new GregorianCalendar(1961, 7, 18)).getTime().getTime()));
        singer.addAlbum(album);

        album = new Album();
        album.setTitle("A Heart Full of Blues");
        album.setReleaseDate(new java.sql.Date(
                (new GregorianCalendar(1962, 3, 20)).getTime().getTime()));
        singer.addAlbum(album);

        singerDao.save(singer);
        assertNotNull(singer.getId());

        List<Singer> singers = singerDao.findAllWithAlbum();
        assertEquals(4, singers.size());
        listSingersWithAlbum(singers);
    }

    @Test
    public void testUpdate() {
        Singer singer = singerDao.findById(1L);
        assertNotNull(singer);
        assertEquals("Mayer", singer.getLastName());
        Album album = singer.getAlbums().stream().filter(
                a -> a.getTitle().equals("Battle Studies")).findFirst().get();
        singer.setFirstName("John Clayton");
        singer.removeAlbum(album);
        singerDao.save(singer);
        listSingersWithAlbum(singerDao.findAllWithAlbum());
    }

    @Test
    public void delete() {
        Singer singer = singerDao.findById(2l);
        assertNotNull(singer);
        singerDao.delete(singer);
        listSingersWithAlbum(singerDao.findAllWithAlbum());
    }

    @After
    public void teardown() {
        if (context != null) {
            context.close();
        }

        singerDao = null;
    }


    private static void listSingers(List<Singer> singers) {
        logger.info(" ---- Listing singers:");
        for (Singer singer : singers) {
            logger.info(singer.toString());
        }
    }

    private static void listSingersWithAlbum(List<Singer> singers) {
        logger.info(" ---- Listing singers with instruments:");
        for (Singer singer : singers) {
            logger.info(singer.toString());
            if (singer.getAlbums() != null) {
                for (Album album :
                        singer.getAlbums()) {
                    logger.info("\t" + album.toString());
                }
            }
            if (singer.getInstruments() != null) {
                for (Instrument instrument : singer.getInstruments()) {
                    logger.info("\t" + instrument.getInstrumentId());
                }
            }
        }
    }
}