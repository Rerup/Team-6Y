using Microsoft.EntityFrameworkCore;
using RepetitionAPI.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading;
using System.Threading.Tasks;

namespace RepetitionAPI.Repos
{
    public class GiftRepo
    {
        private readonly object _lockObj = new object();

        private readonly List<Gift> context = new List<Gift>();

        public GiftRepo()
        {
            context.Add(new Gift
            {
                Title = "Dukke",
                Description = "Minder lidt om en Barbie",
                BoyGift = false,
                GirlGift = true,
                GiftNumber = 1,
                CreationDate = DateTime.Now

            });
        }

        public List<Gift> GetGifts()
        {
            return context;
        }

        public Gift GetGiftByID(int id)
        {
            return context.Find(x => x.GiftNumber == id);
        }

        public void InsertGift(Gift gift)
        {
            context.Add(gift);
        }

        public void DeleteGift(int giftID)
        {
            Gift gift = context.Find(x => x.GiftNumber == giftID);
            context.Remove(gift);
        }

        public void UpdateGift(Gift gift)
        {
            lock (_lockObj) 
            {
                context.Remove(context.Find(x => x.GiftNumber == gift.GiftNumber));
                context.Add(gift);
            }
        }
    }
}
