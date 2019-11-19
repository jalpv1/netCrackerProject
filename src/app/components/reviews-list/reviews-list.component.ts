import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {Review} from '../../models/review'
import {CommonService} from '../../services/common/common.service'
import {Book} from "../../models/book";

@Component({
    selector: 'app-reviews-list',
    templateUrl: './reviews-list.component.html',
    styleUrls: ['./reviews-list.component.css']
})
export class ReviewsListComponent implements OnInit {

    reviews: Review[] = [];

    book: Book = new Book();

    createdReview: Review = new Review();

    constructor(private commonService: CommonService, private route: ActivatedRoute) {
    }

    ngOnInit() {
        this.getAllReviews();
    }

    getAllReviews(): void {
        this.commonService.getReviews(this.book.id).subscribe(
            res => {
                this.reviews = res;
            },
            err => {
                alert("Error in getting all reviews")
            }
        )
    }

    createReview(): void {
        const newCreatedReview: Review = Object.assign({}, this.createdReview);
        this.commonService.createReview(newCreatedReview)
            .subscribe(res => {
                    this.reviews.push(this.createdReview)
                },
                err => {
                    alert("Error in creating new review");
                }
            );
    }
}
