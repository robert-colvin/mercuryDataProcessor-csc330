        program this

! Delcare Variables.
        DOUBLE PRECISION, DIMENSION (3260) ::  lat      !Latitude Data
        DOUBLE PRECISION, DIMENSION (3260) :: long      !Longitude Data
        DOUBLE PRECISION, DIMENSION (3260) :: merc      !Mercury Data
        DOUBLE PRECISION, DIMENSION (3260,3260) ::  mom !Distance Aray
        DOUBLE PRECISION, DIMENSION (3260,3260) ::  dad !Helper Aray
        DOUBLE PRECISION merc2                          !Simivariance Data
        real*8 Dlat                                     !Delta Lat helpter
        real*8 Dlong                                    !Delta Long Helper/Distance helpter
        
        integer cnt                                     !counter
        integer cnt2                                    !counter 2


! Set CNT to 1 to avoid segfaults

        cnt = 1

! Open File

        open ( unit=8, file='./mercurydata.csv', status='old')

! Read Files

10      read (8,*,END=20) lat(cnt), long(cnt), merc (cnt)

! Print Lat Long and Mercury data

!        print *, 'latitude: ', lat (cnt) 
!        print *, 'longitude: ', long (cnt) 
!        print *, 'mercury: ', merc (cnt)

! Convert Degrees to Radians

        lat(cnt) = lat(cnt)*(ATAN(1.0)*4)/180.0
        long(cnt) = long(cnt)*(ATAN(1.0)*4)/180.0
        
!        print *, lat(cnt)
!        print *, long(cnt)

! Increment cnt

        cnt = cnt + 1

! Loop back

        goto 10 	
20      close(8)

! Reset cnt

        cnt = 0	
        
! Find the distances
        
        do i=1,3260,1
                do j=i+1,3260,1


                        Dlong = long(i)-long(j)
                        Dlat = lat(i)-lat(j)

                        mom(i,j) = SIN(Dlat/2.0)**2 + (COS(lat(i)) * cos(lat(j)) * sin(Dlong/2.0)**2)
                        dad(i,j) = ATAN2(SQRT(mom(i,j)), SQRT(1-mom(i,j)))
                        mom(i,j) = 2.0 * 6373000.0 * (dad(i,j))
                        
!                        print *, mom(i,j)
                enddo

                cnt = 0
        enddo

! Calculate Lag and simivariance 

        do cnt2=0,769,1
                Dlong = 0
                do i=1,3260,1
                        do j=i+1,3260,1
                                if (mom(i,j) <= (cnt2+0.5)) then
                                        if (mom(i,j) >= (cnt2-0.5)) then

                                                cnt = cnt+1
                                                Dlong = Dlong + mom(i,j)
                                                merc2 = merc2+((merc(i)-merc(j))**2.0)
                                  
                                         endif                            
                                endif
                        enddo
                        
                enddo

                merc2 = merc2/(2.0*cnt)

                Dlong = Dlong/cnt

! Print the Results

                print *, "there are ", cnt
                print *, "in lag ", cnt2 
                print *, "with an avg of ", Dlong

                print *, "the Simivariance is: ", merc2

                print *
                cnt = 0
        enddo
        end

