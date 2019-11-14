package ua.com.nc.nctrainingproject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.com.nc.nctrainingproject.models.Announcement;
import ua.com.nc.nctrainingproject.services.AnnouncementService;

import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin
public class AnnouncementsController {

  private final AnnouncementService announcementService;

  @Autowired
  public AnnouncementsController(AnnouncementService announcementService){
    this.announcementService = announcementService;
  }

  @RequestMapping(value = "/announcements", method = RequestMethod.GET)
  public List<Announcement> getPublishedAnnouncements(){
    return announcementService.getPublishedAnnouncements();
  }

  @RequestMapping(value = "/announcements/new", method = RequestMethod.GET)
  public List<Announcement> getUnpublishedAnnouncements(){
    return announcementService.getUnpublishedAnnouncements();
  }

  @RequestMapping(value = "/announcements/all", method = RequestMethod.GET)
  public List<Announcement> getAllAnnouncements(){
    return announcementService.getAnnouncements();
  }

  @RequestMapping(value = "/announcement", method = RequestMethod.GET)
  public Announcement getAnnouncement(int id){
    return announcementService.getAnnouncement(id);
  }

  @RequestMapping(value = "/newAnnouncement", method = RequestMethod.POST)
  public void createAnnouncement(
    @RequestParam(name = "description") String description,
    @RequestParam(name = "announcementDate") Date announcementDate,
    @RequestParam(name = "bookID") int bookID,
    @RequestParam(name = "priority") String priority,
    @RequestParam(name = "adminID") int adminID,
    @RequestParam(name = "status") String status
  ){
    Announcement announcement = new Announcement(description, announcementDate, bookID, priority, adminID, status);
    announcementService.createAnnouncement(announcement);
  }

  @RequestMapping(value = "/proposeAnnouncement", method = RequestMethod.POST)
  public void proposeAnnouncement(
    @RequestParam(name = "description") String description,
    @RequestParam(name = "announcementDate") Date announcementDate,
    @RequestParam(name = "bookID") int bookID,
    @RequestParam(name = "priority") String priority,
    @RequestParam(name = "adminID") int adminID
  ){
    Announcement announcement = new Announcement(description, announcementDate, bookID, priority, adminID);
    announcementService.proposeAnnouncement(announcement);
  }

  @RequestMapping(value = "/announcements/new/", method = RequestMethod.POST)
  public void publishAnnouncement(@RequestParam(name = "id") int id){
    announcementService.publishAnnouncement(id);
  }

  @RequestMapping(value = "/announcements/delete", method = RequestMethod.POST)
  public void deleteAnnouncement(@RequestParam(name = "id") int id){
    announcementService.deleteAnnouncement(id);
  }

  @RequestMapping(value = "/announcements/update", method = RequestMethod.POST)
  public void updateAnnouncement(
    @RequestParam(name = "description") String description,
    @RequestParam(name = "announcementDate") Date announcementDate,
    @RequestParam(name = "bookID") int bookID,
    @RequestParam(name = "priority") String priority,
    @RequestParam(name = "adminID") int adminID,
    @RequestParam(name = "status") String status
  ){
    Announcement announcement = new Announcement(description, announcementDate, bookID, priority, adminID, status);
    announcementService.updateAnnouncement(announcement);
  }
}