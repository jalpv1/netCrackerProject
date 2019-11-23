import {Component, OnInit} from '@angular/core';
import {Announcement} from '../../models/announcement';
import {HttpClient} from '@angular/common/http';

@Component({
    selector: 'app-announcement',
    templateUrl: './announcement.component.html',
    styleUrls: ['./announcement.component.css']
})
export class AnnouncementComponent implements OnInit {
    model: Announcement = {
        id: 0,
        title: '',
        description: '',
        date: ''
    };

    constructor(private http: HttpClient) {
    }

    /*
      constructor(private route: ActivatedRoute) { }
      id:any;
      ngOnInit() {
        this.id = parseInt(this.route.snapshot.paramMap.get('id'));

     */

    ngOnInit() {
    }

    createAnnouncement(): void {
        let url = 'http://localhost:8080/announcement';
        let form = new FormData();
        form.append('title', this.model.title);
        form.append('description', this.model.description);
        form.append('date', this.model.date);
        this.http.post(url, form).subscribe(
            res => {
                location.reload();
            },
            err => {
                alert(JSON.parse(JSON.stringify(err)).message);
            }
        );
    }
}