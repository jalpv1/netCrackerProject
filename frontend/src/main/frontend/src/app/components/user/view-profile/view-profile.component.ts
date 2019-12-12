import {Component, Input, OnChanges, OnDestroy, OnInit, SimpleChanges} from '@angular/core';
import {User} from "../../../models/user";
import {UserService} from "../../../services/user/user.service";
import {Router} from "@angular/router";
import {StorageService} from "../../../services/storage/storage.service";
import {Subscription} from "rxjs";

@Component({
    selector: 'app-view-profile',
    templateUrl: './view-profile.component.html',
    styleUrls: ['./view-profile.component.css']
})
export class ViewProfileComponent implements OnInit, OnChanges, OnDestroy {
    @Input() user: User;
    currentUser: User;
    isAbleToAddToFriend: boolean;

    subscription: Subscription;

    constructor(private userService: UserService,
                private router: Router,
                private storageService: StorageService) {
    }

    ngOnInit() {
        this.currentUser = this.storageService.getUser();
    }

    ngOnChanges(changes: SimpleChanges): void {
        this.user = changes.user.currentValue;
        if (this.currentUser && this.currentUser.id != this.user.id && this.currentUser.userRole == 'user') {
            this.subscription = this.userService.checkRequest(this.currentUser.id, this.user.id).subscribe( //TODO Doesn`t appear after other tabs
                () => {
                    this.isAbleToAddToFriend = false;
                },
            () => {
                    this.isAbleToAddToFriend = true;
            }
            );
        }
    }

    ngOnDestroy(): void {
        if (this.subscription) {
            this.subscription.unsubscribe();
        }
    }

    sendRequest() {
        this.userService.sendRequest(this.currentUser.id, this.user.id).subscribe(
            res => {
            },
            err => {
                alert("Error in add friends");
            }
        );
    }
}
