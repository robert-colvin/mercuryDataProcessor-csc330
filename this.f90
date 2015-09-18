        program this

        DOUBLE PRECISION, DIMENSION (3260) ::  lat
        DOUBLE PRECISION, DIMENSION (3260) :: long
        DOUBLE PRECISION, DIMENSION (3260) :: merc
        DOUBLE PRECISION, DIMENSION (3260) ::  mom
        DOUBLE PRECISION, DIMENSION (3260) ::  dad
        DOUBLE PRECISION, DIMENSION (3260) :: merc2
        real*8 Dlat
        real*8 Dlong 
        

        INTEGER cnt

        open ( unit=8, file='./mercurydata.csv', status='old')


10      read (8,*,END=20) lat(cnt), long(cnt), merc (cnt)
        
        print *, 'latitude: ', lat (cnt) 
        print *, 'longitude: ', long (cnt) 
   !     print *, 'mercury: ', merc (cnt)
    !    print *
        lat(cnt) = lat(cnt)*ATAN(-1.0)/180
        long(cnt) = long(cnt)*ATAN(-1.0)/180
        
        print *, lat(cnt)
        print *, long(cnt)
        
        cnt = cnt + 1

        goto 10 	
20      close(8)

        cnt = 0	
        

        
        do i=1,3260,1
                do j=i+1,3260,1


                        Dlong = long(i)-long(j)
                        Dlat = lat(i)-lat(j)

                        mom(j) = SIN(Dlat/2.0)**2 + (COS(lat(i)) * cos(lat(j)) * sin(Dlong/2.0)**2)
                        dad(j) = ASIN(sqrt(mom(j)))
                        mom(j) = 2.0 * 6373000.0 * (dad(j))

  !                      if (mom(j) .lt. 1)
      !                          cnt= cnt+1
      !                          merc2(i) = merc2(i)+(merc(i)-merc(j)**2)/(2*cnt)
!                                  
                                                             
    !                    endif

                        print *, mom(j)
                enddo
        !        print *, merc2((i)
        enddo
        end

