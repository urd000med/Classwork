/*
 * @(#)ExtraStorageMergeSortAlgorithm.java  1.0 97/01/09 Jack Snoeyink
 *
 * Copyright (c) 1995 University of British Columbia
 *
 * Permission to use, copy, modify, and distribute this software
 * and its documentation for NON-COMMERCIAL purposes and without
 * fee is hereby granted provided that this copyright notice
 * appears in all copies. Please refer to the file "copyright.html"
 * for further important copyright and licensing information.
 *
 * UBC MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF
 * THE SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED
 * TO THE IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A
 * PARTICULAR PURPOSE, OR NON-INFRINGEMENT. UBC SHALL NOT BE LIABLE FOR
 * ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR
 * DISTRIBUTING THIS SOFTWARE OR ITS DERIVATIVES.
 */

/**
 * A merge sort demonstration algorithm using extra space
 * SortAlgorithm.java, Thu Oct 27 10:32:35 1994
 *
 * @author Jack Snoeyink@cs.ubc.ca
 * @version 	1.0, 09 Jan 97
 */
class MergeSortAlgorithm extends SortAlgorithm {
    void sort(int a[], int lo, int hi, int scratch[]) throws Exception {
        if (lo >= hi) {
            return;                 /* a[lo] is sorted already   */
        }

        int mid = (lo+hi) / 2;
        sort(a, lo, mid, scratch);      /* Sort sublist a[lo..mid]   */
        sort(a, mid+1, hi, scratch);    /* Sort sublist a[mid+1..hi] */

        int k, t_lo = lo, t_hi = mid+1;  
        for (k = lo; k <= hi; k++)            /* Merge sorted sublists    */
            if ((t_lo <= mid) && ((t_hi > hi) || (a[t_lo] < a[t_hi]))) {
                scratch[k] = a[t_lo++];
                pause(t_lo, t_hi);
            }
            else {
                scratch[k] = a[t_hi++];
               pause(t_lo, t_hi);
            }

        for (k = lo; k <= hi; k++) {
            a[k] = scratch[k]; /* Copy back to a   */
            pause(k,hi);
        }
    }

    void sort(int a[])  throws Exception {
        int scratch[] = new int[a.length];
	sort(a, 0, a.length-1, scratch);
    }
}
