import { Injectable } from '@angular/core';
import * as firebase from 'firebase';
import { AngularFireDatabase } from 'angularfire2/database';
import { Upload } from './upload.model';
import { ActivatedRoute, Router } from '@angular/router';
import { Refreshable } from '../shared/refreshable.service';


@Injectable()
export class UploadService {

  constructor(private db: AngularFireDatabase,
              private route: ActivatedRoute,
              private router: Router) {
  }

  pushUpload(upload: Upload, basePath:  string, databasePath: string, navigateTo: string, refreshable: Refreshable) {
    let storageRef = firebase.storage().ref();
    let uploadTask = storageRef.child(`${basePath}/${upload.file.name}`).put(upload.file);
    upload.progress = 0;
    uploadTask.on(firebase.storage.TaskEvent.STATE_CHANGED,
      (snapshot) =>  {
        // upload in progress
        upload.progress = (uploadTask.snapshot.bytesTransferred / uploadTask.snapshot.totalBytes) * 100;
      },
      (error) => {
        // upload failed
        console.log(error);
      },
      () => {
        // upload success
        upload.url = uploadTask.snapshot.downloadURL;
        upload.name = upload.file.name;

        this.saveFileData(upload, databasePath);

        if(navigateTo) {
          this.router.navigate([navigateTo]);
        }

        if(refreshable) {
          refreshable.refresh();
        }
      }
    );
  }
  // Writes the file details to the realtime db
  saveFileData(upload: Upload, databasePath: string) {
    this.db.list(`${databasePath}/`).push(upload);
  }

  deleteUpload(upload: Upload, basePath: string, databasePath: string, navigateTo: string) {
    this.deleteFileData(upload.$key, databasePath)
    .then( () => {
      this.deleteFileStorage(upload.name, basePath);

      if(navigateTo) {
        this.router.navigate([navigateTo]);
      }
    })
    .catch(error => console.log(error))
  }
  // Deletes the file details from the realtime db
  private deleteFileData(key: string, basePath: string) {
    return this.db.list(`${basePath}/`).remove(key);
  }
  // Firebase files must have unique names in their respective storage dir
  // So the name serves as a unique key
  private deleteFileStorage(name: string, basePath: string) {
    let storageRef = firebase.storage().ref();
    storageRef.child(`${basePath}/${name}`).delete()
  }
}
