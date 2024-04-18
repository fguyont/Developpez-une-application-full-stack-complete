import { Component, OnInit } from '@angular/core';
import { Subject } from 'src/app/models/subject';
import { SubjectService } from 'src/app/services/subject.service';

@Component({
  selector: 'app-subject',
  templateUrl: './subject.component.html',
  styleUrls: ['./subject.component.scss']
})
export class SubjectComponent implements OnInit {

  subjects: Subject[] | undefined;

  constructor(private subjectService: SubjectService,) { }

  ngOnInit(): void {
    this.getAll();
  }

  public getAll(): void {
    this.subjectService.getAll().subscribe((subjects) => {
      this.subjects = subjects;
    })
  }

  public follow(id: number) {
    this.subjectService.follow(id.toString()).subscribe(() => {
    });
  }

}
